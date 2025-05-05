import { StyleData } from "../../../types/style";
import { API_URL } from "../../../util/Constants";
import useDictionary from '../../../hooks/useDictionary.tsx';


interface FirstScreenProps {
    goToNextStep: () => void;
    formState: StyleData;
    logoUrl: string;
    backgroundUrl: string;
    dictionaryData : any ;
}

const FirstScreen: React.FC<FirstScreenProps> = ({ goToNextStep, formState, logoUrl, backgroundUrl, dictionaryData }) => {
    const handleClick = () => {
        goToNextStep();
    };

    return (
        <div style={{ backgroundImage: backgroundUrl != '' ? `url(${API_URL + '/' + backgroundUrl})` : "none", backgroundSize: 'cover', backgroundPosition: 'center' }} className="w-full h-full rounded-6xl p-3.5">
            <div className="justify-items-center">
                {logoUrl != '' && <img src={API_URL + '/' + logoUrl} alt="Logo" className="w-40" />}
            </div>
            <div className="mb-4.5">
                <label className={`mb-2.5 block text-black dark:text-white font-medium`} style={{ fontSize: formState.lablel?.size, fontStyle: formState.lablel?.fontStyle, fontWeight: formState.lablel?.fontWeight, color: `#${formState.lablel?.color}` }}>
                    (*label) {useDictionary(dictionaryData, "please")}              </label>
                <input
                    type="email"
                    placeholder={useDictionary(dictionaryData, "personal")}
                    style={{ borderRadius: formState.textField?.type, fontSize: formState.textField?.size, color: `#${formState.textField?.color}`, backgroundColor: `#${formState.textField?.background}`, fontStyle: formState.textField?.fontStyle, fontWeight: formState.textField?.fontWeight }}
                    className={`w-full border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary`}
                />
            </div>

            <button onClick={handleClick}
                style={{ borderRadius: formState.button?.type, fontSize: formState.button?.size, color: `#${formState.button?.color}`, backgroundColor: `#${formState.button?.background}`, fontStyle: formState.button?.fontStyle, fontWeight: formState.button?.fontWeight, borderColor: `#${formState.button?.borderColor}` }}
                className={`flex w-full justify-center p-3 font-medium hover:bg-opacity-90 mt-20`}>
                (*button) {useDictionary(dictionaryData, "signin")}
            </button>
            <div className={`mt-4 text-center text-black dark:text-white`} style={{ fontSize: formState.hint?.size, fontStyle: formState.hint?.fontStyle, fontWeight: formState.hint?.fontWeight, color: `#${formState.hint?.color}` }}>
                (*hint) {useDictionary(dictionaryData, "forgot")}
            </div>
        </div>
    )
}

export default FirstScreen