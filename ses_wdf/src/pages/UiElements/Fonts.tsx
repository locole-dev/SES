import { useState, useRef, useEffect } from 'react';
import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import ConfirmBox from '../../components/ConfirmBox';
import { Edit, Trash } from '../../components/Icons';
import TableFooter from '../../components/TableFooter';
import apiService from '../../service/ApiService';
import { ApParam } from '../../types/ap-param';

const Fonts = () => {

  const [refresh, setRefresh] = useState(false);
  const [isUpdate, setIsUpdate] = useState(false);
  const [name, setName] = useState("");
  const [value, setValue] = useState("");
  const [id, setId] = useState<number | null>(null);
  const [errors, setErrors] = useState<{ name?: string; value?: string }>({});

  const [data, setData] = useState<ApParam[]>([]);

  // setup pagination
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const totalPages = Math.ceil(data.length / rowsPerPage);
  const displayedData = data.slice(
    (currentPage - 1) * rowsPerPage,
    currentPage * rowsPerPage
  );
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };
  // ----

  // setup dialog
  const [showConfirm, setShowConfirm] = useState<number | null>(null);
  const [position, setPosition] = useState<{ top: number; left: number; translateX?: string } | null>(null);
  const buttonRef = useRef<HTMLButtonElement | null>(null);

  const handleShowConfirm = (id: number, event: React.MouseEvent) => {
    const rect = (event.target as HTMLElement).getBoundingClientRect();
    setPosition({
      top: rect.top - 100, // Đẩy lên trên
      left: rect.left - 120, // Căn giữa
      translateX: "-20%",
    });
    setShowConfirm(id);
  };

  const handleDelete = async () => {
    setShowConfirm(null);
    const result = await apiService({ method: "DELETE", url: `/deleteApParam/${showConfirm}` });

    if (result) {
      setRefresh((prev) => !prev);
    }
  };
  // ----

  // setup fonts
  const [fonts, setFonts] = useState<any[]>([]);
  const [fontWeight, setFontWeight] = useState<any[]>([]);
  const [fontStyle, setFontStyle] = useState<any[]>([]);
  useEffect(() => {
    fetch("/gui/data.json")
      .then((res) => res.json())
      .then((data) => {
        if (data.fonts) {
          const fontsArray = Object.keys(data.fonts).map((key) => ({
            value: data.fonts[key].value,
            label: data.fonts[key].label,
          }));
          setFonts(fontsArray);
        }

        if (data.fontWeight) {
          const fontWeightArray = Object.keys(data.fontWeight).map((key) => ({
            value: data.fontWeight[key].value,
            label: data.fontWeight[key].label,
          }));
          setFontWeight(fontWeightArray);
        }

        if (data.fontStyle) {
          const fontStyleArray = Object.keys(data.fontStyle).map((key) => ({
            value: data.fontStyle[key].value,
            label: data.fontStyle[key].label,
          }));
          setFontStyle(fontStyleArray);
        }
      })
      .catch((error) => console.error("Lỗi khi tải dữ liệu font:", error));
  }, []);
  // ----

  useEffect(() => {
    const fetchData = async () => {
      const result = await apiService({ url: `/findAllApParamByType/size` });
      if (result) setData(result);
    };

    fetchData();
  }, [refresh]);

  const onUpdate = async (value: ApParam) => {
    setIsUpdate(true);
    setId(value.id);
    setName(value.name);
    setValue(value.value);
    setErrors({});
  };

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateForm()) return;
    let data: any = {
      name: name.trim(),
      value: value.trim(),
      type: 'size'
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
      setRefresh((prev) => !prev);
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

  return (
    <>
      <Breadcrumb pageName="Fonts" />
      <div className="grid grid-cols-5 gap-8">
        <div className="col-span-5 xl:col-span-3">
          <div className="flex flex-col gap-10">
            <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
              <div className="max-w-full overflow-x-auto">
                <div className="flex pb-4">
                  <div className="flext-none text-black dark:text-white text-lg font-medium">
                    List of Size
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
                            <button className="hover:text-primary" onClick={() => onUpdate(packageItem)}>
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
                    List of Font
                  </div>
                  <div className="grow"></div>
                  <div className="flext-none">
                  </div>
                </div>
                <div className="mb-4">
                  <div className="grid grid-cols-3 gap-6">
                    {fonts.map((font, index) => (
                      <div key={index} className={`shadow inline-flex items-center justify-start rounded-xl bg-stone-50 p-2 text-start font-medium text-white hover:bg-opacity-90 ${font.value ?? ''}`}>
                        <div className="border border-indigo-600 rounded-xl text-black p-2 bg-white border-stone-300 font-bold text-xl">Aa</div>
                        <div className="flex flex-col ml-[20px] text-black">
                          <div>{font.label}</div>
                          <div className='text-stone-400 font-base'>Base</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="flex flex-col gap-10 mt-8">
            <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
              <div className="max-w-full overflow-x-auto">
                <div className="flex pb-4">
                  <div className="flext-none text-black dark:text-white text-lg font-medium">
                    Font Style
                  </div>
                  <div className="grow"></div>
                  <div className="flext-none">
                  </div>
                </div>
                <div className="mb-4">
                  <div className="grid grid-cols-3 gap-6">
                    {fontStyle.map((font, index) => (
                      <div key={index} className={`shadow inline-flex items-center justify-start rounded-xl bg-stone-50 p-2 text-start font-medium text-white hover:bg-opacity-90 ${font.value ?? ''}`}>
                        <div className="border border-indigo-600 rounded-xl text-black p-2 bg-white border-stone-300 font-bold text-xl" style={{ fontStyle: font.value }}>Aa</div>
                        <div className="flex flex-col ml-[20px] text-black">
                          <div style={{ fontStyle: font.value }}>{font.label}</div>
                          <div className='text-stone-400 font-base' style={{ fontStyle: font.value }}>Style</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="flex flex-col gap-10 mt-8">
            <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
              <div className="max-w-full overflow-x-auto">
                <div className="flex pb-4">
                  <div className="flext-none text-black dark:text-white text-lg font-medium">
                    Font Weight
                  </div>
                  <div className="grow"></div>
                  <div className="flext-none">
                  </div>
                </div>
                <div className="mb-4">
                  <div className="grid grid-cols-3 gap-6">
                    {fontWeight.map((font, index) => (
                      <div key={index} className={`shadow inline-flex items-center justify-start rounded-xl bg-stone-50 p-2 text-start font-medium text-white hover:bg-opacity-90 ${font.value ?? ''}`}>
                        <div className="border border-indigo-600 rounded-xl text-black p-2 bg-white border-stone-300 text-xl" style={{ fontWeight: font.value }}>Aa</div>
                        <div className="flex flex-col ml-[20px] text-black">
                          <div style={{ fontWeight: font.value }}>{font.label}</div>
                          <div className='text-stone-400 font-base'>{font.value}</div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
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

export default Fonts;
