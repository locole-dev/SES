const TableFooter = ({ currentPage, totalPages, rowsPerPage, onPageChange, onPageSizeChange, showItemPage = true }: { currentPage: number, totalPages: number, rowsPerPage: number, onPageChange: (page: number) => void, onPageSizeChange: (page: number) => void, showItemPage?: boolean }) => {
    const pageSizes = [5, 10, 20, 50];

    return (
        <div className="flex justify-between items-center py-4 border-t border-[#eee] dark:border-strokedark">
            {showItemPage && (
                <div className="flex-1 items-center space-x-2">
                    <select
                        value={rowsPerPage}
                        onChange={(e) => onPageSizeChange(Number(e.target.value))}
                        className="px-3 py-2 border border-gray-300 rounded-md dark:bg-meta-4"
                    >
                        {pageSizes.map((size) => (
                            <option key={size} value={size}>{size}</option>
                        ))}
                    </select>
                    <span className="text-gray-600">items per page</span>
                </div>
            )}
            <span className="text-sm text-gray-600 dark:text-white mr-4">
                Page {currentPage} of {totalPages}
            </span>
            <div className="flex space-x-2">
                <button
                    className="px-3 py-1 text-sm bg-gray-200 dark:bg-meta-4 rounded hover:bg-gray-300 dark:hover:bg-meta-3"
                    disabled={currentPage === 1}
                    onClick={() => onPageChange(currentPage - 1)}
                >
                    Prev
                </button>
                <button
                    className="px-3 py-1 text-sm bg-gray-200 dark:bg-meta-4 rounded hover:bg-gray-300 dark:hover:bg-meta-3"
                    disabled={currentPage === totalPages}
                    onClick={() => onPageChange(currentPage + 1)}
                >
                    Next
                </button>
            </div>
        </div>
    );
};

export default TableFooter;
