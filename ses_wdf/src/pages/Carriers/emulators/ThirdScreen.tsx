import { StyleData } from "../../../types/style";
import { API_URL } from "../../../util/Constants";
import useDictionary from '../../../hooks/useDictionary.tsx';

interface ThirdScreenProps {
    goBackStep: () => void;
    formState: StyleData;
    logoUrl: string;
    backgroundUrl: string;
    dictionaryData: any;
}

const ThirdScreen: React.FC<ThirdScreenProps> = ({ goBackStep, formState, logoUrl, backgroundUrl, dictionaryData}) => {

    const handleBackStepClick = () => {
        goBackStep();
    };
    return (
        <div style={{ backgroundImage: backgroundUrl != '' ? `url(${API_URL + '/' + backgroundUrl})` : "none", backgroundSize: 'cover', backgroundPosition: 'center' }} className="w-full h-full rounded-6xl p-3.5">
            <div className="justify-items-center">
                {logoUrl != '' && <img src={API_URL + '/' + logoUrl} alt="Logo" className="w-40" />}
            </div>
            <div className="mb-10 block text-black dark:text-white font-bold text-xl text-center" style={{ fontSize: formState.header?.size, fontStyle: formState.header?.fontStyle, fontWeight: formState.header?.fontWeight, color: `#${formState.header?.color}` }}>
                (*header {useDictionary(dictionaryData, "received")})
            </div>
            <div className="text-sm mt-4 text-center text-black dark:text-white mb-20" style={{ fontSize: formState.content?.size, fontStyle: formState.content?.fontStyle, fontWeight: formState.content?.fontWeight, color: `#${formState.content?.color}` }}>
                (*content) {useDictionary(dictionaryData, "received-content")}
            </div>
            <button onClick={handleBackStepClick} style={{ borderRadius: formState.cancelButton?.type, fontSize: formState.cancelButton?.size, color: `#${formState.cancelButton?.color}`, backgroundColor: `#${formState.cancelButton?.background}`, borderColor: `#${formState.cancelButton?.borderColor}` }}
                className={`flex w-full justify-center border bg-white p-3 font-medium text-black hover:bg-opacity-90 mt-4`}>
                (*cancel button) Huỷ bỏ
            </button>
        </div>
    )
}

export default ThirdScreen