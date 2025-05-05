import { Option } from "../types/option";
import { useEffect, useState } from "react";
import { API_URL } from "../util/Constants";
import TableFooter from "./TableFooter";
import { Tick } from "./Icons/Tick";

interface DialogProps {
    title: string;
    message: string;
    isOpen: boolean;
    data: Option[];
    defaultValue?: any;
    onClose: () => void;
    onConfirm: (data: any) => void;
}

const CustomDialog = ({ title, message, isOpen, data, defaultValue, onClose, onConfirm }: DialogProps) => {
    const [value, setValue] = useState(defaultValue);
    // setup pagination
    const [currentPage, setCurrentPage] = useState(1);
    const [rowsPerPage, setRowsPerPage] = useState(8);
    const totalPages = Math.ceil(data.length / rowsPerPage);
    const displayedData = data.slice(
        (currentPage - 1) * rowsPerPage,
        currentPage * rowsPerPage
    );
    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };
    // ----

    useEffect(() => {
        if (isOpen) {
            setValue(defaultValue);
        }
    }, [isOpen, defaultValue]);

    const handleConfirm = () => {
        onConfirm(value);
        onClose();
        setValue(null);
    };

    if (!isOpen) return null; // Ẩn dialog khi không mở

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
            <div className="bg-white dark:bg-gray-800 rounded-lg shadow-lg p-6" style={{ width: "500px" }}>
                <h2 className="text-lg font-semibold text-gray-900 dark:text-white">{title}</h2>
                <p className="text-gray-600 dark:text-gray-300 mt-1 mb-4.5">{message}</p>
                <div className="grid grid-cols-4 gap-4">
                    {displayedData?.map((item) => (
                        <div key={item.value} className="relative w-24 h-40 cursor-pointer" onClick={() => {
                            if (value == item.value) {
                                setValue(null);
                            } else {
                                setValue(item.value);
                            }
                        }}>
                            <img src={API_URL + '/' + item.image} alt={item.label} className="w-full h-full object-cover" />
                            {value == item.value && (
                                <div className="absolute top-1 right-1 bg-white rounded-full">
                                    <Tick width={20} height={20} />
                                </div>
                            )}
                        </div>
                    ))}
                </div>

                <TableFooter
                    currentPage={currentPage}
                    totalPages={totalPages}
                    rowsPerPage={rowsPerPage}
                    onPageSizeChange={setRowsPerPage}
                    onPageChange={handlePageChange}
                    showItemPage={false}
                />

                <div className="mt-4 flex justify-end gap-2">
                    <button className="px-4 py-2 bg-gray-300 text-gray-800 rounded-md hover:bg-gray-400" onClick={onClose}>
                        Cancel
                    </button>
                    <button className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700" onClick={handleConfirm}>
                        Confirm
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CustomDialog;
