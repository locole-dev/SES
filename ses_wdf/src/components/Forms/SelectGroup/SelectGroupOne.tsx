import React from "react";
import Select from "react-select";
import { API_URL } from "../../../util/Constants";
import { Option } from "../../../types/option";

interface SelectGroupProps {
  title: string;
  isSearchable?: boolean;
  defaultValue?: string;
  options: Option[];
  onChange: (selectedValue: string) => void;
}

const SelectGroup: React.FC<SelectGroupProps> = ({ title, options, defaultValue, isSearchable, onChange }) => {

  return (
    <div>
      <label className="mb-2.5 block text-black dark:text-white">{title}</label>
      <Select
        value={options.find(e => e.value === defaultValue)}
        isSearchable={isSearchable ?? true}
        options={options}
        isOptionDisabled={(e) => e.disbled === true}
        formatOptionLabel={e => (
          <div className="flex items-center">
            {e.image && <img src={API_URL + '/' + e.image} alt={e.label} className="mr-2" width={40} />}
            {e.hexColor && <div className="w-5 h-5 rounded-md mr-2 border border border-meta-600" style={{ backgroundColor: e.hexColor }} />}
            <span>{e.label}</span>
          </div>
        )}
        onChange={(selectedOption) => onChange(selectedOption?.value || "")}
        className="w-full dark:text-white"
        classNames={{
          control: () => "dark:bg-form-input dark:text-white dark:border-form-strokedark p-1.5",
          group: () => "dark:bg-form-input dark:text-white",
          input: () => "dark:text-white",
          menu: () => "dark:bg-form-input dark:text-white",
          multiValueLabel: () => "dark:bg-form-input dark:text-white",
          multiValueRemove: () => "dark:bg-form-input dark:text-white",
          option: () => "dark:hover:bg-meta-4 dark:text-white",
          placeholder: () => "dark:text-white",
          singleValue: () => "dark:text-white",
        }}
      />
    </div>
  );
};

export default SelectGroup;
