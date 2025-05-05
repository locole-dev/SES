import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import { Option } from '../../../types/option.ts';
import React, { useEffect, useState } from 'react';

const animatedComponents = makeAnimated();

interface MultiSelectGroupProps {
  title: string;
  options?: Option[];
  defaultValue?: Option[];
  isDisabled?: boolean;
  onChange?: (selectedValues: any[]) => void;
}

const MultiSelectGroup: React.FC<MultiSelectGroupProps> = ({
  title,
  defaultValue = [],
  isDisabled = false,
  options = [],
  onChange,
}) => {
  const [updatedOptions, setUpdatedOptions] = useState<Option[]>(options);
  useEffect(() => {
    setUpdatedOptions(options);
  }, [options]);
  const handleChange = (selectedOption: any[]) => {
    const isAllSelected = selectedOption.some(
      (option) => option.label === 'All',
    );
    const newOptions = options.map((option) => ({
      ...option,
      isDisabled:
        isAllSelected ||
        (selectedOption.length === 0 ? false : option.label === 'All'),
    }));
    setUpdatedOptions(newOptions);
    onChange && onChange(selectedOption);
  };

  return (
    <div>
      {defaultValue.length >= 0 && isDisabled ? (
        <div>
          <label className="mb-2.5 block text-black dark:text-white">
            {title}
          </label>
          <Select
            closeMenuOnSelect={false}
            components={animatedComponents}
            isMulti
            options={updatedOptions}
            value={defaultValue.length > 0 ? defaultValue : options.filter(item => item.label === 'All')}
            onChange={handleChange}
            isDisabled={isDisabled}
            className="w-full dark:text-white"
            classNames={{
              control: () =>
                'dark:bg-form-input dark:text-white dark:border-form-strokedark p-1.5',
              group: () => 'dark:bg-form-input dark:text-white',
              input: () => 'dark:text-white',
              menu: () => 'dark:bg-form-input dark:text-white',
              multiValueLabel: () => 'dark:bg-form-input dark:text-white',
              multiValueRemove: () => 'dark:bg-form-input dark:text-white',
              option: () => 'dark:hover:bg-meta-4 dark:text-white',
              placeholder: () => 'dark:text-white',
              singleValue: () => 'dark:text-white',
            }}
          />
        </div>
      ) : (
        <div>
          <label className="mb-2.5 block text-black dark:text-white">
            {title}
          </label>
          <Select
            closeMenuOnSelect={false}
            components={animatedComponents}
            isMulti
            options={updatedOptions}
            onChange={handleChange}
            isDisabled={isDisabled}
            className="w-full dark:text-white"
            classNames={{
              control: () =>
                'dark:bg-form-input dark:text-white dark:border-form-strokedark p-1.5',
              group: () => 'dark:bg-form-input dark:text-white',
              input: () => 'dark:text-white',
              menu: () => 'dark:bg-form-input dark:text-white',
              multiValueLabel: () => 'dark:bg-form-input dark:text-white',
              multiValueRemove: () => 'dark:bg-form-input dark:text-white',
              option: () => 'dark:hover:bg-meta-4 dark:text-white',
              placeholder: () => 'dark:text-white',
              singleValue: () => 'dark:text-white',
            }}
          />
        </div>
      )}
    </div>
  );
};

export default MultiSelectGroup;
