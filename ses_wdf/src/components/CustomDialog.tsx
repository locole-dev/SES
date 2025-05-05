import { Option } from "../types/option";
import SelectGroup from "./Forms/SelectGroup/SelectGroupOne";
import { TextStyle } from "../types/style";
import { useEffect, useState } from "react";

interface DialogProps {
    title: string;
    message: string;
    isOpen: boolean;
    formState: TextStyle;
    isText?: boolean;
    isButton?: boolean;
    isTextField?: boolean;
    dataButton?: Option[];
    dataColor?: Option[];
    dataSize?: Option[];
    fontWeight?: Option[];
    fontStyle?: Option[];
    onClose: () => void;
    onConfirm: (data: TextStyle) => void;
}

const CustomDialog = ({ title, message, isOpen, formState, isText, isButton, isTextField, dataButton, dataColor, dataSize, fontStyle, fontWeight, onClose, onConfirm }: DialogProps) => {
    const [formStateUpdate, setFormState] = useState<TextStyle>(formState);
    useEffect(() => {
        if (isOpen) {
            setFormState(formState);
        }
    }, [isOpen, formState]);

    const handleConfirm = () => {
        onConfirm(formStateUpdate);
        onClose();
        setFormState({});
    };

    if (!isOpen) return null; // Ẩn dialog khi không mở

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
            <div className="bg-white dark:bg-gray-800 rounded-lg shadow-lg p-6" style={{ width: "500px" }}>
                <h2 className="text-lg font-semibold text-gray-900 dark:text-white">{title}</h2>
                <p className="text-gray-600 dark:text-gray-300 mt-1 mb-4.5">{message}</p>
                {isButton && (
                    <div>
                        <div className="mb-4.5">
                            <SelectGroup
                                title="Button Type"
                                defaultValue={formStateUpdate.type}
                                options={dataButton ?? []}
                                onChange={(value) => {
                                    setFormState((prev) => ({ ...prev, type: value }))
                                }}
                            />
                        </div>
                        <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                            <div className="w-full xl:w-1/2">
                                <SelectGroup
                                    title="Button Color"
                                    defaultValue={formStateUpdate.background}
                                    options={dataColor ?? []}
                                    onChange={(value) => {
                                        setFormState((prev) => ({ ...prev, background: value }))
                                    }}
                                /></div>
                            <div className="w-full xl:w-1/2">
                                <SelectGroup
                                    title="Border Button Color"
                                    defaultValue={formStateUpdate.borderColor}
                                    options={dataColor ?? []}
                                    onChange={(value) => {
                                        setFormState((prev) => ({ ...prev, borderColor: value }))
                                    }}
                                />
                            </div>
                        </div>
                    </div>
                )}
                {isTextField && (
                    <div>
                        <div className="mb-4.5">
                            <SelectGroup
                                title="TextField Type"
                                defaultValue={formStateUpdate.type}
                                options={dataButton ?? []}
                                onChange={(value) => {
                                    setFormState((prev) => ({ ...prev, type: value }))
                                }}
                            />
                        </div>
                    </div>
                )}
                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                    <div className="w-full xl:w-1/2">
                        <SelectGroup
                            title="Font Size"
                            defaultValue={formStateUpdate.size}
                            options={dataSize ?? []}
                            onChange={(value) => {
                                setFormState((prev) => ({ ...prev, size: value }))
                            }}
                        />
                    </div>
                    <div className="w-full xl:w-1/2">
                        <SelectGroup
                            title="Font Style"
                            defaultValue={formStateUpdate.fontStyle}
                            options={fontStyle ?? []}
                            onChange={(value) => {
                                setFormState((prev) => ({ ...prev, fontStyle: value }))
                            }}
                        />
                    </div>
                </div>
                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                    <div className="w-full xl:w-1/2">
                        <SelectGroup
                            title="Font Weight"
                            defaultValue={formStateUpdate.fontWeight}
                            options={fontWeight ?? []}
                            onChange={(value) => {
                                setFormState((prev) => ({ ...prev, fontWeight: value }))
                            }}
                        />
                    </div>
                    <div className="w-full xl:w-1/2">
                        <SelectGroup
                            title="Text Color"
                            defaultValue={formStateUpdate.color}
                            options={dataColor ?? []}
                            onChange={(value) => {
                                setFormState((prev) => ({ ...prev, color: value }))
                            }}
                        />
                    </div>
                </div>

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
