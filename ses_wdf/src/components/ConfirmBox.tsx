import { useRef, useEffect } from "react";
import ReactDOM from "react-dom";

const ConfirmBox = ({ position, onClose, onConfirm }: any) => {
    const boxRef = useRef<HTMLDivElement | null>(null);

    // Tự đóng nếu click ra ngoài
    useEffect(() => {
        const handleClickOutside = (event: MouseEvent) => {
            if (boxRef.current && !boxRef.current.contains(event.target as Node)) {
                onClose();
            }
        };
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [onClose]);

    return ReactDOM.createPortal(
        <div
            ref={boxRef}
            className="fixed bg-white border shadow-lg p-3 rounded"
            style={{
                top: position.top,
                left: position.left,
                transform: `translateX(${position.translateX ?? "-50%"})`,
                zIndex: 9999, // Luôn hiển thị trên cùng
            }}
        >
            <p>Are you sure you want to delete?</p>
            <div className="flex justify-end gap-2 mt-2">
                <button onClick={onClose} className="px-3 py-1 bg-gray-300 rounded">
                    Cancel
                </button>
                <button onClick={onConfirm} className="px-3 py-1 bg-red-500 text-white rounded">
                    Confirm
                </button>
            </div>
        </div>,
        document.body
    );
};

export default ConfirmBox;