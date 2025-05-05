import { useNavigate } from "react-router-dom";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import { Carrier } from "../../types/carrier";
import { useEffect, useRef, useState } from "react";
import apiService from "../../service/ApiService";
import TableFooter from "../../components/TableFooter";
import ConfirmBox from "../../components/ConfirmBox";
import { Edit, Eye, Paint, Plus, Search, Trash } from "../../components/Icons";
import { API_URL } from "../../util/Constants";

const Carriers = () => {

  const navigate = useNavigate();
  const [refresh, setRefresh] = useState(false);

  const [data, setData] = useState<Carrier[]>([]);
  const [filteredData, setFilteredData] = useState<Carrier[]>([]);
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
    const data = {
      "id": showConfirm
    }
    const result = await apiService({ method: "DELETE", data: data, url: "/saveCarrier" });

    if (result) {
      setRefresh((prev) => !prev);
    }
  };
  // ----
  useEffect(() => {
    const fetchData = async () => {
      const result = await apiService({ url: "/listCarrier" });
      if (result) { setData(result); setFilteredData(result) };
    };

    fetchData();
  }, [refresh]);

  const onInsert = (e: React.FormEvent) => {
    e.preventDefault();
    navigate('/pages/carriers/form');
  };

  const onUpdate = (e: React.FormEvent, id: number) => {
    e.preventDefault();
    navigate(`/pages/carriers/form/${id}`);
  };

  const onLink = (e: React.FormEvent, id: number) => {
    e.preventDefault();
    navigate(`/pages/carriers/mappings/${id}`);
  };

  const onSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value.toLowerCase();
    const result = data.filter((item) =>
      item.name.toLowerCase().includes(value)
    );
    setFilteredData(result);
    setCurrentPage(1);
  }

  return (
    <>
      <Breadcrumb pageName="Carriers" />

      <div className="flex flex-col gap-10">
        <div className="rounded-lg border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
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
                    Name
                  </th>
                  <th className="min-w-[150px] py-4 px-4 font-medium text-black dark:text-white">
                    Issue date
                  </th>
                  <th className="min-w-[120px] py-4 px-4 font-medium text-black dark:text-white">
                    Status
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
                        <div className="h-12.5 w-15 rounded-md flex items-center">
                          <img src={API_URL + '/' + packageItem.logoUrl} alt={packageItem.name} />
                        </div>
                        <p className="text-sm text-black dark:text-white">
                          {packageItem.name}
                        </p>
                      </div>
                    </td>
                    <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                      <p className="text-black dark:text-white">
                        {packageItem.createdDate}

                      </p>
                    </td>
                    <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark">
                      <p
                        className={`inline-flex rounded-full bg-opacity-10 py-1 px-3 text-sm font-medium ${packageItem.status === 0
                          ? 'bg-success text-success'
                          : 'bg-danger text-danger'
                          }`}
                      >
                        {packageItem.status === 0 ? 'Active' : 'Inactive'}
                      </p>
                    </td>
                    <td className="border-b border-[#eee] py-5 px-4 dark:border-strokedark justify-items-end pr-11">
                      <div className="flex items-center space-x-3.5">
                        <button className="hover:text-primary" onClick={(e) => onLink(e, packageItem.id)} >
                          <Paint width={22} height={22} />
                        </button>
                        <button className="hover:text-primary" onClick={(e) => onUpdate(e, packageItem.id)}>
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
    </>
  );
};

export default Carriers;
