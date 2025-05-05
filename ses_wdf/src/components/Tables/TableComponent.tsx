import React from 'react';
import { DataTable } from '../../types/data-table';

// Lấy tên trường làm tên cột, nên nếu muốn custom dữ liệu thì tạo DTO riêng cast từ dữ liệu gốc
interface TableProps {
  table: DataTable;
}
const TableComponent: React.FC<TableProps> = ({ table }) => {
  const columns = Object.keys(table.data[0]);

  return (
    <div className="rounded-sm border border-stroke bg-white px-5 pt-6 pb-2.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:pb-1">
      <h4 className="mb-6 text-xl font-semibold text-black dark:text-white">
        {table.title ?? ''}
      </h4>

      <div className="flex flex-col">
        <div className="grid grid-cols-3 rounded-sm bg-gray-2 dark:bg-meta-4 sm:grid-cols-5">
          {columns.map((col, index) => (
            <div key={index} className="p-2.5 xl:p-5">
              <h5 className="text-sm font-medium uppercase xsm:text-base">
                {col}
              </h5>
            </div>
          ))}
        </div>
        {table.data?.map((brand, key) => (
          <div
            className={`grid grid-cols-3 sm:grid-cols-5 ${key === table.data?.length ?? 1 - 1
              ? ''
              : 'border-b border-stroke dark:border-strokedark'
              }`}
            key={key}
          >
            <div className="flex items-center gap-3 p-2.5 xl:p-5">
              <div className="flex-shrink-0">
                <img src={brand.logo} alt="Brand" />
              </div>
              <p className="hidden text-black dark:text-white sm:block">
                {brand.name}
              </p>
            </div>

            <div className="flex items-center justify-center p-2.5 xl:p-5">
              <p className="text-black dark:text-white">{brand.visitors}K</p>
            </div>

            <div className="flex items-center justify-center p-2.5 xl:p-5">
              <p className="text-meta-3">${brand.revenues}</p>
            </div>

            <div className="hidden items-center justify-center p-2.5 sm:flex xl:p-5">
              <p className="text-black dark:text-white">{brand.sales}</p>
            </div>

            <div className="hidden items-center justify-center p-2.5 sm:flex xl:p-5">
              <p className="text-meta-5">{brand.conversion}%</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TableComponent;
