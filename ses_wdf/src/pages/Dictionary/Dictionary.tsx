import Breadcrumb from '../../components/Breadcrumbs/Breadcrumb';
import ConfirmBox from '../../components/ConfirmBox';
import { Search, Plus } from '../../components/Icons';
import { Edit } from '../../components/Icons/Edit';
import { Trash } from '../../components/Icons/Trash';
import TableFooter from '../../components/TableFooter';
import apiService from '../../service/ApiService';
import { DictionaryType } from '../../types/dictionary';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from "react-router-dom";

const Dictionary = () => {

  const [refresh, setRefresh] = useState(false);
  const [data, setData] = useState<DictionaryType[]>([]);
  const [filteredData, setFilteredData] = useState<DictionaryType[]>([]);

  // setup pagination
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const totalPages = Math.ceil(filteredData.length / rowsPerPage);
  const displayedData = filteredData.slice(
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
    console.log(showConfirm);
    setShowConfirm(null);
    const result = await apiService({ method: "DELETE", url: `/deleteDictionary/${showConfirm}` });

    if (result) {
      setRefresh((prev) => !prev);
    }
  };
  // ----

  useEffect(() => {
    const fetchDictionary = async () => {
      const result = await apiService({ url: "/listDictionary" });
      if (result) { setData(result); setFilteredData(result) };
    };

    fetchDictionary();
  }, [refresh]);

  const navigate = useNavigate();

  const onInsert = (e: React.FormEvent) => {
    e.preventDefault();
    navigate('/settings/dictionary/form');
  };

  const onUpdate = (e: React.FormEvent, id: number) => {
    e.preventDefault();
    navigate(`/settings/dictionary/form/${id}`);
  };


  const onSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value.toLowerCase();
    const result = data.filter((item) =>
      item.key.toLowerCase().includes(value)
    );
    setFilteredData(result);
    setCurrentPage(1);
  }


  return (
    <>
      <div className="mx-auto max-w-270">
        <Breadcrumb pageName="Dictionary" />

        <div className="grid grid-cols-6 gap-8">
          <div className="col-span-6 xl:col-span-6">
            <div className="flex flex-col gap-10">
              <div className="rounded-md border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
                <div className="max-w-full overflow-x-auto">
                  <div className="flex pb-6">
                    <div className="flext-none">
                      <div className="relative">
                        <span className="absolute left-4.5 top-2.5">
                          <Search />
                        </span>
                        <input
                          onChange={onSearch}
                          className="w-full rounded-full border border-stroke bg-gray py-2 pl-11.5 pr-4.5 text-black focus:border-primary focus-visible:outline-none dark:border-strokedark dark:bg-meta-4 dark:text-white dark:focus:border-primary"
                          type="email"
                          name="emailAddress"
                          id="emailAddress"
                          placeholder="Search for name"
                        />
                      </div>
                    </div>
                    <div className="grow"></div>
                    <div className="flext-none">
                      <button onClick={onInsert} className="flex w-full justify-items-start rounded-full bg-primary px-4 py-2 font-medium text-gray hover:bg-opacity-90">
                        <span className="mr-2">
                          <Plus width={20} height={20} />
                        </span>
                        Add New
                      </button>
                    </div>
                  </div>
                  <table className="w-full table-auto">
                    <thead>
                      <tr className="bg-gray-2 text-left dark:bg-meta-4">
                        <th className="min-w-[220px] py-4 px-4 font-medium text-black dark:text-white xl:pl-11">
                          Key
                        </th>
                        <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                          Vietnamese
                        </th>
                        <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                          English
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
                              <p className="text-black dark:text-white">
                                {packageItem.key}
                              </p>
                            </div>
                          </td>
                          <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                            <p className="text-black dark:text-white truncate max-w-64 leading-6">
                              {packageItem.vi}
                            </p>
                          </td>
                          <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                            <p className="text-black dark:text-white truncate max-w-64 leading-6 ">
                              {packageItem.eng}
                            </p>
                          </td>
                          <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark justify-items-end pr-11">
                            <div className="flex items-center space-x-3.5">
                              {/* <button className="hover:text-primary">
                                <Eye />
                              </button> */}
                              <button className="hover:text-primary" onClick={(e) => {
                                onUpdate(e, packageItem.id);
                              }}>
                                <Edit width={18} height={18} />
                              </button>
                              <button className="hover:text-primary" ref={buttonRef}
                                onClick={(e) => handleShowConfirm(packageItem.id!, e)}>
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
          {/* <div className="col-span-6 xl:col-span-2">
            <div className="rounded-md border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
              <div className="border-b border-stroke py-4 px-7 dark:border-strokedark">
                <h3 className="font-medium text-black dark:text-white">
                  {isUpdate ? "Update" : "Add"} Dictionary
                </h3>
              </div>
              <div className="p-7">
                <form onSubmit={onSubmit}>
                  <div className="flex items-center gap-3">
                    <div className="mb-4.5 w-full">
                      <label className="mb-2.5 block text-black dark:text-white">
                        Key
                      </label>
                      <input
                        value={key}
                        onChange={(e) => {
                          setKey(e.target.value);
                          if (errors.key) {
                            setErrors((prev) => ({ ...prev, key: undefined }));
                          }
                        }}
                        type="text"
                        placeholder="Enter upload name"
                        className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                      />
                      {errors.key && <p className="text-red-500 text-sm mt-1">{errors.key}</p>}
                    </div>
                  </div>
                  <div className="flex items-center gap-3">
                    <div className="mb-4.5 w-full">
                      <label className="mb-2.5 block text-black dark:text-white">
                        Vietnamese
                      </label>
                      <input
                        value={vi}
                        onChange={(e) => {
                          setVi(e.target.value);
                          if (errors.vi) {
                            setErrors((prev) => ({ ...prev, vi: undefined }));
                          }
                        }}
                        type="text"
                        placeholder="Enter upload name"
                        className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                      />
                      {errors.vi && <p className="text-red-500 text-sm mt-1">{errors.vi}</p>}
                    </div>
                  </div>
                  <div className="mb-4 flex items-center gap-3">
                    <div className="mb-4.5 w-full">
                      <label className="mb-2.5 block text-black dark:text-white">
                        English
                      </label>
                      <input
                        value={eng}
                        onChange={(e) => {
                          setEng(e.target.value);
                          if (errors.eng) {
                            setErrors((prev) => ({ ...prev, eng: undefined }));
                          }
                        }}
                        type="text"
                        placeholder="Enter upload name"
                        className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                      />
                      {errors.eng && <p className="text-red-500 text-sm mt-1">{errors.eng}</p>}
                    </div>
                  </div>

                  <div className="flex justify-end gap-4.5">
                    {isUpdate && <button onClick={() => {
                      setIsUpdate(false);
                      setKey("");
                      setVi("");
                      setEng("");
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
          </div> */}
        </div>
      </div>
    </>
  );
};

export default Dictionary;
