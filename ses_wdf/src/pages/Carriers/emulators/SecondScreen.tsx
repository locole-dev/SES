import { StyleData } from "../../../types/style";
import { API_URL } from "../../../util/Constants";
import useDictionary from '../../../hooks/useDictionary.tsx';

interface SecondScreenProps {
    goToNextStep: () => void;
    goBackStep: () => void;
    formState: StyleData;
    logoUrl: string;
    backgroundUrl: string;
    dictionaryData: any;
}


const SecondScreen: React.FC<SecondScreenProps> = ({ goToNextStep, goBackStep, formState, logoUrl, backgroundUrl, dictionaryData }) => {
    const handleNextStepClick = () => {
        goToNextStep();
    };

    const handleBackStepClick = () => {
        goBackStep();
    };
    return (
        <div style={{ backgroundImage: backgroundUrl != '' ? `url(${API_URL + '/' + backgroundUrl})` : "none", backgroundSize: 'cover', backgroundPosition: 'center' }} className="w-full h-full rounded-6xl p-3.5">
            <div className='relative h-full'>
                <div className="justify-items-center">
                    {logoUrl != '' && <img src={API_URL + '/' + logoUrl} alt="Logo" className="w-40" />}
                </div>
                <div className="mb-10 block text-black dark:text-white font-bold text-xl text-center" style={{ fontSize: formState.header?.size, fontStyle: formState.header?.fontStyle, fontWeight: formState.header?.fontWeight, color: `#${formState.header?.color}` }}>
                    (*header){useDictionary(dictionaryData, "term-conditions")}
                </div>
                <div style={{ fontSize: formState.content?.size, fontStyle: formState.content?.fontStyle, fontWeight: formState.content?.fontWeight, color: `#${formState.content?.color}` }} className="text-sm mt-4 text-left text-black dark:text-white mb-20">
                    (*content) {useDictionary(dictionaryData, "term-conditions-content")}
                </div>

                <div className="w-full flex flex-col items-center">
                    <div className='absolute bottom-2 right-0 left-0'>
                        <button onClick={handleNextStepClick}
                            style={{ borderRadius: formState.button?.type, fontSize: formState.button?.size, color: `#${formState.button?.color}`, backgroundColor: `#${formState.button?.background}`, fontStyle: formState.button?.fontStyle, fontWeight: formState.button?.fontWeight, borderColor: `#${formState.button?.borderColor}` }}
                            className={`flex w-full justify-center bg-primary p-3 font-medium text-gray hover:bg-opacity-90 mt-20`}>
                            (*button) Đồng ý và tiếp tục
                        </button>
                        <button onClick={handleBackStepClick}
                            style={{ borderRadius: formState.cancelButton?.type, fontSize: formState.cancelButton?.size, color: `#${formState.cancelButton?.color}`, backgroundColor: `#${formState.cancelButton?.background}`, fontStyle: formState.cancelButton?.fontStyle, fontWeight: formState.cancelButton?.fontWeight, borderColor: `#${formState.cancelButton?.borderColor}` }}
                            className={`flex w-full justify-center border border-black bg-white p-3 font-medium text-black hover:bg-opacity-90 mt-4`}>
                            (*cancel button) Huỷ bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SecondScreen