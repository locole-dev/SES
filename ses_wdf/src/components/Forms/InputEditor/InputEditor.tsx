import React, { useState } from "react";
import { PaintEdit } from "../../Icons/PaintEdit";
import CustomDialog from "../../CustomDialog";
import { Option } from "../../../types/option";
import { TextStyle } from "../../../types/style";

interface InputEditorProps {
  title: string;
  formState: TextStyle;
  isText?: boolean;
  isButton?: boolean;
  isTextField?: boolean;
  dataButton?: Option[];
  dataColor?: Option[];
  dataSize?: Option[];
  fontWeight?: Option[];
  fontStyle?: Option[];
  onChange: (selectedValue: TextStyle) => void;
}

const InputEditor: React.FC<InputEditorProps> = ({ title, formState, isText, isButton, dataButton, dataColor, dataSize, isTextField, fontStyle, fontWeight, onChange }) => {

  const [isDialogOpen, setDialogOpen] = useState(false);

  const handleConfirm = (data: TextStyle) => {
    onChange(data);
  };

  return (
    <div>
      <label className="mb-2.5 block text-black dark:text-white">{title}</label>
      <div className="flex items-center rounded border-[1.5px] border-stone-300 bg-transparent p-1.5 text-black outline-none transition dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
        <div className="flex grow px-2 py-0.5 items-center  overflow-hidden">
          {isButton && (
            <div className="flex items-center flex-wrap">
              <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-2 border-blue-500 rounded-md cursor-context-menu text-blue-500">{dataButton?.find((item) => item.value === formState.type)?.label.replaceAll('rounded-', '')}</div>
              <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-dashed rounded-md cursor-context-menu">
                <div className="w-6 h-6 rounded-md border" style={{ backgroundColor: `#${formState.background}` }} />
              </div>
            </div>
          )}
          {isTextField && (
            <div className="flex items-center">
              <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-2 border-blue-500 rounded-md cursor-context-menu text-blue-500" style={{ borderRadius: formState.type }}>{dataButton?.find((item) => item.value === formState.type)?.label.replaceAll('rounded-', '')}</div>
            </div>
          )}

          <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-dashed rounded-md cursor-context-menu">{dataSize?.find((item) => item.value === formState.size)?.label.replaceAll('text-', '')}</div>
          <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-dashed rounded-md cursor-context-menu" style={{ fontWeight: formState.fontWeight }}>{fontWeight?.find((item) => item.value === formState.fontWeight)?.label.charAt(0)}</div>
          <div className="mr-2 italic font-bold w-8 h-8 items-center justify-center flex border border-dashed rounded-md cursor-context-menu" style={{ fontStyle: formState.fontStyle }}>{fontStyle?.find((item) => item.value === formState.fontStyle)?.label.charAt(0)}</div>
          <div className="mr-2 font-bold w-8 h-8 items-center justify-center flex border border-dashed rounded-md cursor-context-menu" style={{
            color: `#${formState.color}`, textShadow: "0 0 0.6px black, 0 0 0.6px black, 0 0 0.6px black, 0 0 0.6px black"}}>
            <div >A</div>
          </div>
        </div>
        <div className="flex flex-none">
          <div className="h-9 py-2">
            <span className="border-l-[1px] border-solid border-stone-300	" />
          </div>
          <div className="w-9 h-9 p-2 cursor-pointer items-center justify-center" onClick={() => setDialogOpen(true)}>
            <PaintEdit width={22} height={22} fill="#800000"/>
          </div>
        </div>
      </div>

      <CustomDialog
        title={title}
        message="Please select the options you want to change."
        isOpen={isDialogOpen}
        onClose={() => setDialogOpen(false)}
        onConfirm={handleConfirm}
        formState={formState}
        isText={isText}
        isButton={isButton}
        isTextField={isTextField}
        dataButton={dataButton}
        dataColor={dataColor}
        dataSize={dataSize}
        fontStyle={fontStyle}
        fontWeight={fontWeight}
      />
    </div>
  );
};

export default InputEditor;
