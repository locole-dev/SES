import React, { useState } from "react";
import { PaintEdit } from "../../Icons/PaintEdit";
import { Option } from "../../../types/option";
import CustomDialogBasic from "../../CustomDialogBasic";

interface CustomInputEditorProps {
  title: string;
  data: Option[];
  defaultValue?: any;
  preivewDefault?: any;
  onChange: (selectedValue: any) => void;
}

const CustomInputEditor: React.FC<CustomInputEditorProps> = ({ title, data, defaultValue, onChange }) => {

  const [isDialogOpen, setDialogOpen] = useState(false);

  const handleConfirm = (value: any) => {
    onChange(value);
  };

  return (
    <div>
      <label className="mb-2.5 block text-black dark:text-white">{title}</label>
      <div className="flex items-center rounded border-[1.5px] border-stone-300 bg-transparent p-1.5 text-black outline-none transition dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
        <div className="flex grow px-2 py-0.5 items-center  overflow-hidden">
          <div>Select item</div>
        </div>
        <div className="flex flex-none">
          <div className="h-9 py-2">
            <span className="border-l-[1px] border-solid border-stone-300	" />
          </div>
          <div className="w-9 h-9 p-2 cursor-pointer items-center justify-center" onClick={() => setDialogOpen(true)}>
            <PaintEdit width={22} height={22} fill="#800000" />
          </div>
        </div>
      </div>

      <CustomDialogBasic
        title={title}
        message="Please select the options you want to change."
        isOpen={isDialogOpen}
        data={data}
        defaultValue={defaultValue}
        onClose={() => setDialogOpen(false)}
        onConfirm={handleConfirm}
      />
    </div>
  );
};

export default CustomInputEditor;
