import React from "react";
import { API_URL } from "../util/Constants";

interface ImagePreviewModalProps {
    isOpen: boolean;
    imageUrl: string | null;
    onClose: () => void;
}

const ImagePreviewModal: React.FC<ImagePreviewModalProps> = ({ isOpen, imageUrl, onClose }) => {
    if (!isOpen || !imageUrl) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-4 rounded-lg relative">
                <button className="absolute top-2 right-2 text-black" onClick={onClose}>
                    x
                </button>
                <img src={API_URL + '/' + imageUrl} alt="Preview" className="max-w-[80vw] max-h-[80vh]" />
            </div>
        </div>
    );
};

export default ImagePreviewModal;
