import Breadcrumb from '../components/Breadcrumbs/Breadcrumb';
import { Edit } from '../components/Icons/Edit';
import { Trash } from '../components/Icons/Trash';
import { useEffect, useRef, useState } from 'react';
import { Logo } from '../types/logo';
import apiService from '../service/ApiService';
import TableFooter from '../components/TableFooter';
import ConfirmBox from '../components/ConfirmBox';
import { API_URL } from '../util/Constants';
import SelectGroup from '../components/Forms/SelectGroup/SelectGroupOne';
import { Option } from "../types/option";

const FileManager = () => {

  const [refresh, setRefresh] = useState(false);
  const [data, setData] = useState<Logo[]>([]);
  const [file, setFile] = useState<File | null>(null);
  const [previewUrl, setPreviewUrl] = useState<string | null>(null);
  const [id, setId] = useState<number | null>(null);
  const [isUpdate, setIsUpdate] = useState(false);
  const [errors, setErrors] = useState<{ name?: string; file?: string }>({});
  const [listType, setListType] = useState<Option[]>([{ value: "0", label: "Carriers" }, { value: "1", label: "SmartWatch" }, { value: "2", label: "Background" }]);
  const [type, setType] = useState("0");
  const fileInputRef = useRef<HTMLInputElement | null>(null);

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
  const [position, setPosition] = useState<{ top: number; left: number } | null>(null);
  const buttonRef = useRef<HTMLButtonElement | null>(null);

  const handleShowConfirm = (id: number, event: React.MouseEvent) => {
    const rect = (event.target as HTMLElement).getBoundingClientRect();
    setPosition({
      top: rect.top - 100, // Đẩy lên trên
      left: rect.left - 40, // Căn giữa
    });
    setShowConfirm(id);
  };

  const handleDelete = async () => {
    setShowConfirm(null);
    const result = await apiService({ method: "DELETE", url: `/deleteLogo/${showConfirm}` });

    if (result) {
      setRefresh((prev) => !prev);
    }
  };
  // ----

  useEffect(() => {
    const fetchLogo = async () => {
      const result = await apiService({ url: "/listLogos" });
      if (result) setData(result);
    };

    fetchLogo();
  }, [refresh]);


  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0];
    if (selectedFile) {
      setPreviewUrl(URL.createObjectURL(selectedFile));
      setErrors((prev) => ({ ...prev, file: undefined }));
      setFile(selectedFile);
    }
  };

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validateForm()) return;
    const formData = new FormData();
    formData.append("type", type);
    if (file) {
      formData.append("file", file);
    }
    if (isUpdate) {
      formData.append("id", id!.toString());
      if (previewUrl) {
        formData.append("data", previewUrl.replace(API_URL + '/', ''));
      }
    }

    const result = await apiService({ method: isUpdate ? "PUT" : "POST", url: "/upload", data: formData, headers: { "Content-Type": "multipart/form-data" } });

    if (result) {
      if (fileInputRef.current) {
        fileInputRef.current.value = '';
      }
      setFile(null);
      setPreviewUrl("");
      setErrors({});
      setRefresh((prev) => !prev);
      setIsUpdate(false);
    }
  };

  const onUpdate = async (value: Logo) => {
    setIsUpdate(true);
    setId(value.id);
    setPreviewUrl(API_URL + '/' + value.data);
    setType(value.type.toString());
    setErrors({});
  };


  const validateForm = () => {
    const newErrors: { file?: string } = {};

    if (!file && !isUpdate) {
      newErrors.file = "Vui lòng chọn ảnh tải lên!";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }

  return (
    <>
      <div className="mx-auto max-w-270">
        <Breadcrumb pageName="File Manager" />

        <div className="grid grid-cols-5 gap-8">
          <div className="col-span-5 xl:col-span-3">
            <div className="flex flex-col gap-10">
              <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
                <div className="max-w-full overflow-x-auto">
                  <table className="w-full table-auto">
                    <thead>
                      <tr className="bg-gray-2 text-left dark:bg-meta-4">
                        <th className="min-w-[220px] py-4 px-4 font-medium text-black dark:text-white xl:pl-11">
                          Name
                        </th>
                        <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                          Type
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
                              <div className="h-12.5 w-15 rounded-md items-center flex justify-center">
                                <img src={API_URL + '/' + packageItem.data} alt="Product" className='max-w-15 max-h-12.5' />
                              </div>
                            </div>
                          </td>
                          <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                            <p className="text-black dark:text-white">
                              {packageItem.type === 0 ? 'Carriers' : packageItem.type === 1 ? 'SmartWatch' : 'Background'}
                            </p>
                          </td>
                          <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark justify-items-end pr-11">
                            <div className="flex items-center space-x-3.5 relative">
                              {/* <button className="hover:text-primary" onClick={() => {
                                openModal(packageItem.data);
                              }}>
                                <Eye />
                              </button> */}
                              <button className="hover:text-primary" onClick={() => onUpdate(packageItem)}>
                                <Edit width={18} height={18} />
                              </button>
                              <button
                                ref={buttonRef}
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
          </div>
          <div className="col-span-5 xl:col-span-2">
            <div className="rounded-lg border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
              <div className="border-b border-stroke py-4 px-7 dark:border-strokedark">
                <h3 className="font-medium text-black dark:text-white">
                  {isUpdate ? "Update" : "Add"} Photo
                </h3>
              </div>
              <div className="p-7">
                <form onSubmit={onSubmit}>
                  <div className="mb-4 flex items-center gap-3">
                    <div className="mb-4.5 w-full">
                      <SelectGroup
                        defaultValue={type}
                        title="Type"
                        options={listType}
                        onChange={(value) => {
                          setType(value);
                        }}
                      />
                    </div>
                  </div>
                  <div
                    id="FileUpload"
                    className={`relative block w-full cursor-pointer appearance-none rounded border border-dashed border-primary bg-gray py-4 px-4 dark:bg-meta-4 sm:py-7.5 z-0 ${errors.file ? "" : "mb-5.5"}`}>
                    <input
                      ref={fileInputRef}
                      onChange={handleFileChange}
                      type="file"
                      accept="image/*"
                      className="absolute inset-0 z-40 m-0 h-full w-full cursor-pointer p-0 opacity-0 outline-none"
                    />
                    <div className="flex flex-col items-center justify-center space-y-3">
                      <span className="flex h-10 w-10 items-center justify-center rounded-full border border-stroke bg-white dark:border-strokedark dark:bg-boxdark">
                        <svg
                          width="16"
                          height="16"
                          viewBox="0 0 16 16"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            fillRule="evenodd"
                            clipRule="evenodd"
                            d="M1.99967 9.33337C2.36786 9.33337 2.66634 9.63185 2.66634 10V12.6667C2.66634 12.8435 2.73658 13.0131 2.8616 13.1381C2.98663 13.2631 3.1562 13.3334 3.33301 13.3334H12.6663C12.8431 13.3334 13.0127 13.2631 13.1377 13.1381C13.2628 13.0131 13.333 12.8435 13.333 12.6667V10C13.333 9.63185 13.6315 9.33337 13.9997 9.33337C14.3679 9.33337 14.6663 9.63185 14.6663 10V12.6667C14.6663 13.1971 14.4556 13.7058 14.0806 14.0809C13.7055 14.456 13.1968 14.6667 12.6663 14.6667H3.33301C2.80257 14.6667 2.29387 14.456 1.91879 14.0809C1.54372 13.7058 1.33301 13.1971 1.33301 12.6667V10C1.33301 9.63185 1.63148 9.33337 1.99967 9.33337Z"
                            fill="#3C50E0"
                          />
                          <path
                            fillRule="evenodd"
                            clipRule="evenodd"
                            d="M7.5286 1.52864C7.78894 1.26829 8.21106 1.26829 8.4714 1.52864L11.8047 4.86197C12.0651 5.12232 12.0651 5.54443 11.8047 5.80478C11.5444 6.06513 11.1223 6.06513 10.8619 5.80478L8 2.94285L5.13807 5.80478C4.87772 6.06513 4.45561 6.06513 4.19526 5.80478C3.93491 5.54443 3.93491 5.12232 4.19526 4.86197L7.5286 1.52864Z"
                            fill="#3C50E0"
                          />
                          <path
                            fillRule="evenodd"
                            clipRule="evenodd"
                            d="M7.99967 1.33337C8.36786 1.33337 8.66634 1.63185 8.66634 2.00004V10C8.66634 10.3682 8.36786 10.6667 7.99967 10.6667C7.63148 10.6667 7.33301 10.3682 7.33301 10V2.00004C7.33301 1.63185 7.63148 1.33337 7.99967 1.33337Z"
                            fill="#3C50E0"
                          />
                        </svg>
                      </span>
                      <p>
                        <span className="text-primary">Click to upload</span> or
                        drag and drop
                      </p>
                      {!previewUrl && (
                        <div className="text-center mt-1.5">
                          <p>SVG, PNG or JPG</p>
                          <p>(max, 1Mbs)</p>
                        </div>
                      )}
                      {previewUrl && (
                        <div className="mt-4">
                          <img
                            src={previewUrl}
                            alt="Preview"
                            className="mx-auto mt-2 h-20 w-20 object-contain rounded-lg shadow-lg bg-white"
                          />
                        </div>
                      )}
                    </div>
                  </div>
                  {errors.file && <p className="text-red-500 text-sm mt-1">{errors.file}</p>}

                  <div className="flex justify-end gap-4.5">
                    {isUpdate && <button onClick={() => {
                      setIsUpdate(false);
                      setPreviewUrl("");
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
        </div >
      </div >
    </>
  );
};

export default FileManager;
