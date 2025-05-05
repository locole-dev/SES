import { Link, useNavigate } from 'react-router-dom';

import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import { useState, useRef, useEffect } from 'react';
import ConfirmBox from '../../components/ConfirmBox';
import { Plus, Paint, Edit, Trash } from '../../components/Icons';
import TableFooter from '../../components/TableFooter';
import apiService from '../../service/ApiService';
import { ApParam } from '../../types/ap-param';
import CheckboxFive from '../../components/Checkboxes/CheckboxFive';

const Buttons = () => {

  const navigate = useNavigate();
  const [refresh, setRefresh] = useState(false);
  const [refresh2, setRefresh2] = useState(false);
  const [isChecked, setIsChecked] = useState<boolean>(true);
  const [isUpdate, setIsUpdate] = useState(false);
  const [name, setName] = useState("");
  const [value, setValue] = useState("");
  const [id, setId] = useState<number | null>(null);
  const [errors, setErrors] = useState<{ name?: string; value?: string }>({});

  const [dataButton, setDataButton] = useState<ApParam[]>([]);
  const [dataColor, setDataColor] = useState<ApParam[]>([]);
  // setup pagination
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const totalPages = Math.ceil(dataButton.length / rowsPerPage);
  const displayedData = dataButton.slice(
    (currentPage - 1) * rowsPerPage,
    currentPage * rowsPerPage
  );
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };
  // ----

  // setup pagination
  const [currentPage2, setCurrentPage2] = useState(1);
  const [rowsPerPage2, setRowsPerPage2] = useState(5);
  const totalPages2 = Math.ceil(dataColor.length / rowsPerPage2);
  const displayedData2 = dataColor.slice(
    (currentPage2 - 1) * rowsPerPage2,
    currentPage2 * rowsPerPage2
  );
  const handlePageChange2 = (page: number) => {
    setCurrentPage2(page);
  };
  // ----

  // setup dialog
  const [showConfirm, setShowConfirm] = useState<number | null>(null);
  const [showConfirm2, setShowConfirm2] = useState<number | null>(null);
  const [position, setPosition] = useState<{ top: number; left: number; translateX?: string } | null>(null);
  const buttonRef = useRef<HTMLButtonElement | null>(null);
  const buttonRef2 = useRef<HTMLButtonElement | null>(null);

  const handleShowConfirm = (id: number, event: React.MouseEvent) => {
    const rect = (event.target as HTMLElement).getBoundingClientRect();
    setPosition({
      top: rect.top - 100, // Đẩy lên trên
      left: rect.left - 120, // Căn giữa
      translateX: "-20%",
    });
    setShowConfirm(id);
  };

  const handleShowConfirm2 = (id: number, event: React.MouseEvent) => {
    const rect = (event.target as HTMLElement).getBoundingClientRect();
    setPosition({
      top: rect.top - 100, // Đẩy lên trên
      left: rect.left - 120, // Căn giữa
      translateX: "-20%",
    });
    setShowConfirm2(id);
  };

  const handleDelete = async () => {
    setShowConfirm(null);
    const result = await apiService({ method: "DELETE", url: `/deleteApParam/${showConfirm}` });

    if (result) {
      setRefresh((prev) => !prev);
    }
  };

  const handleDelete2 = async () => {
    setShowConfirm2(null);
    const result = await apiService({ method: "DELETE", url: `/deleteApParam/${showConfirm2}` });

    if (result) {
      setRefresh2((prev) => !prev);
    }
  };
  // ----
  useEffect(() => {
    const fetchData = async () => {
      const result = await apiService({ url: `/findAllApParamByType/button` });
      if (result) setDataButton(result);
    };

    fetchData();
  }, [refresh]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await apiService({ url: `/findAllApParamByType/color` });
      if (result) setDataColor(result);
    };

    fetchData();
  }, [refresh2]);

  const onUpdate = async (value: ApParam) => {
    setIsUpdate(true);
    setId(value.id);
    setName(value.name);
    setValue(value.value);
    setIsChecked(value.type == 'button' ? true : false);
    setErrors({});
  };

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateForm()) return;
    let data: any = {
      name: name.trim(),
      value: value.trim(),
      type: isChecked ? 'button' : 'color'
    }

    if (isUpdate) {
      data['id'] = id;
    }


    const result = await apiService({ method: isUpdate ? "PUT" : "POST", url: "/saveApParam", data: data });

    if (result) {
      setName("");
      setValue("");
      setId(null);
      setErrors({});
      setIsUpdate(false);
      if (isChecked) {
        setRefresh((prev) => !prev);
      } else {
        setRefresh2((prev) => !prev);
      }
    }
  }


  const validateForm = () => {
    const newErrors: { name?: string; value?: string } = {};

    if (!name) {
      newErrors.name = "Tên không được để trống!";
    }

    if (!value && !isUpdate) {
      newErrors.value = "Value không được để trống!";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }


  const isDarkColor = (color: string): boolean => {
    // Kiểm tra xem color có đúng format hex không (#abc hoặc #aabbcc)
    const hex = color.replace("#", "");
    if (!/^([0-9A-F]{3}){1,2}$/i.test(hex)) return false;

    let r: number, g: number, b: number;

    if (hex.length === 3) {
      // Chuyển từ "#abc" → "#aabbcc"
      r = parseInt(hex[0] + hex[0], 16);
      g = parseInt(hex[1] + hex[1], 16);
      b = parseInt(hex[2] + hex[2], 16);
    } else {
      // Chuyển từ "#abcdef"
      r = parseInt(hex.substring(0, 2), 16);
      g = parseInt(hex.substring(2, 4), 16);
      b = parseInt(hex.substring(4, 6), 16);
    }

    // Tính toán độ sáng (luminance) theo công thức tiêu chuẩn
    const brightness = (r * 299 + g * 587 + b * 114) / 1000;

    return brightness < 128; // Nếu nhỏ hơn 128 thì là màu tối
  };


  return (
    <>
      <Breadcrumb pageName="Buttons & Colors" />
      <div className="grid grid-cols-5 gap-8">
        <div className="col-span-5 xl:col-span-3">
          <div className="flex flex-col gap-10">
            <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
              <div className="max-w-full overflow-x-auto">
                <div className="flex pb-4">
                  <div className="flext-none text-black dark:text-white text-lg font-medium">
                    List of Buttons
                  </div>
                  <div className="grow"></div>
                  <div className="flext-none">
                  </div>
                </div>
                <table className="w-full table-auto">
                  <thead>
                    <tr className="bg-gray-2 text-left dark:bg-meta-4">
                      <th className="min-w-[220px] py-4 px-4 font-medium text-black dark:text-white xl:pl-11">
                        Name
                      </th>
                      <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                        Value
                      </th>
                      <th className="py-4 px-4 font-medium text-black dark:text-white text-right pr-11">
                        Actions
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    {displayedData.map((packageItem, key) => (
                      <tr key={key}>
                        <td className="border-b border-[#eee] py-5 px-4 pl-9 dark:border-strokedark xl:pl-11">
                          <div className="flex flex-col gap-4 sm:flex-row sm:items-center">
                            <p className="text-sm text-black dark:text-white">
                              {packageItem.name}
                            </p>
                          </div>
                        </td>
                        <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                          <p className="text-black dark:text-white">
                            {packageItem.value}

                          </p>
                        </td>
                        <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark justify-items-end pr-11">
                          <div className="flex items-center space-x-3.5">
                            <button className="hover:text-primary" onClick={(e) => onUpdate(packageItem)}>
                              <Edit width={18} height={18} />
                            </button>
                            <button className="hover:text-primary" ref={buttonRef}
                              onClick={(e) => handleShowConfirm(packageItem.id, e)}>
                              <Trash />
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
                <TableFooter
                  currentPage={currentPage}
                  totalPages={totalPages}
                  rowsPerPage={rowsPerPage}
                  onPageSizeChange={setRowsPerPage}
                  onPageChange={handlePageChange}
                />
                {showConfirm !== null && position && (
                  <ConfirmBox position={position} onClose={() => setShowConfirm(null)} onConfirm={handleDelete} />
                )}
              </div>
            </div>
          </div>

          <div className="flex flex-col gap-10 mt-8">
            <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
              <div className="max-w-full overflow-x-auto">
                <div className="flex pb-4">
                  <div className="flext-none text-black dark:text-white text-lg font-medium">
                    List of Colors
                  </div>
                  <div className="grow"></div>
                  <div className="flext-none">
                  </div>
                </div>
                <table className="w-full table-auto">
                  <thead>
                    <tr className="bg-gray-2 text-left dark:bg-meta-4">
                      <th className="min-w-[220px] py-4 px-4 font-medium text-black dark:text-white xl:pl-11">
                        Name
                      </th>
                      <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                        Value
                      </th>
                      <th className="py-4 px-4 font-medium text-black dark:text-white text-right pr-11">
                        Actions
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    {displayedData2.map((packageItem, key) => (
                      <tr key={key}>
                        <td className="border-b border-[#eee] py-5 px-4 pl-9 dark:border-strokedark xl:pl-11">
                          <div className="flex flex-col gap-4 sm:flex-row sm:items-center">
                            <p className="text-sm text-black dark:text-white">
                              {packageItem.name}
                            </p>
                          </div>
                        </td>
                        <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                          <p className={`max-w-fit p-1 ${isDarkColor(`#${packageItem.value}`) ? "text-white" : "text-black"
                            }`} style={{ backgroundColor: `#${packageItem.value}` }}>
                            {packageItem.value}
                          </p>
                        </td>
                        <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark justify-items-end pr-11">
                          <div className="flex items-center space-x-3.5">
                            <button className="hover:text-primary" onClick={(e) => onUpdate(packageItem)}>
                              <Edit width={18} height={18} />
                            </button>
                            <button className="hover:text-primary" ref={buttonRef2}
                              onClick={(e) => handleShowConfirm2(packageItem.id, e)}>
                              <Trash />
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
                <TableFooter
                  currentPage={currentPage2}
                  totalPages={totalPages2}
                  rowsPerPage={rowsPerPage2}
                  onPageSizeChange={setRowsPerPage2}
                  onPageChange={handlePageChange2}
                />
                {showConfirm2 !== null && position && (
                  <ConfirmBox position={position} onClose={() => setShowConfirm2(null)} onConfirm={handleDelete2} />
                )}
              </div>
            </div>
          </div>
        </div>
        <div className="col-span-5 xl:col-span-2">
          <div className="rounded-lg border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
            <div className="border-b border-stroke py-4 px-7 dark:border-strokedark">
              <h3 className="font-medium text-black dark:text-white">
                {isUpdate ? "Update" : "Add"} Param
              </h3>
            </div>
            <div className="p-7">
              <form onSubmit={onSubmit}>
                <div className="mb-4 flex items-center gap-3">
                  <div className="mb-4.5 w-full">
                    <label className="mb-2.5 block text-black dark:text-white">
                      Name
                    </label>
                    <input
                      value={name}
                      onChange={(e) => {
                        setName(e.target.value);
                        if (errors.name) {
                          setErrors((prev) => ({ ...prev, name: undefined }));
                        }
                      }}
                      type="text"
                      placeholder="Enter name"
                      className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                    />
                    {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
                  </div>
                </div>
                <div className="mb-4 flex items-center gap-3">
                  <div className="mb-4.5 w-full">
                    <label className="mb-2.5 block text-black dark:text-white">
                      Value
                    </label>
                    <input
                      value={value}
                      onChange={(e) => {
                        setValue(e.target.value);
                        if (errors.value) {
                          setErrors((prev) => ({ ...prev, value: undefined }));
                        }
                      }}
                      type="text"
                      placeholder="Enter value"
                      className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                    />
                    {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
                  </div>
                </div>
                <div className="mb-4 flex items-center gap-3">
                  <div className="mb-4.5 w-full flex">
                    <label className="mb-2.5 block text-black dark:text-white">
                      Type
                    </label>
                    <div className="flex ml-8">
                      <div className='flex-auto'>
                        <CheckboxFive
                          checked={isChecked}
                          onChange={() => {
                            if (!isChecked) {
                              setIsChecked(true);
                            }
                          }}
                          title="Button"
                        />
                      </div>
                      <div className='flex-auto ml-4'>
                        <CheckboxFive
                          checked={!isChecked}
                          onChange={() => {
                            if (isChecked) {
                              setIsChecked(false);
                            }
                          }}
                          title="Color"
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div className="flex justify-end gap-4.5">
                  {isUpdate && <button onClick={() => {
                    setIsUpdate(false);
                    setName("");
                    setValue("");
                    setErrors({});
                  }}
                    className="flex justify-center rounded border border-stroke py-2 px-6 font-medium text-black hover:shadow-1 dark:border-strokedark dark:text-white"
                    type="button"
                  >
                    Cancel
                  </button>}
                  <button
                    className="flex justify-center rounded bg-primary py-2 px-6 font-medium text-gray hover:bg-opacity-90"
                    type="submit"
                  >
                    Save
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

    </>
  );
};

export default Buttons;
