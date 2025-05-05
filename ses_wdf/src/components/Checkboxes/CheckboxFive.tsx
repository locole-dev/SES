import { useId } from "react";

interface CheckboxFiveProps {
  checked: boolean;
  onChange: () => void;
  title: string;
}

const CheckboxFive = ({ checked, onChange, title }: CheckboxFiveProps) => {
  const id = useId();
  return (
    <div>
      <label
        htmlFor={id}
        className="flex cursor-pointer select-none items-center"
      >
        <div className="relative">
          <input
            type="checkbox"
            id={id}
            className="sr-only"
            onChange={() => onChange()}
          />
          <div
            className={`box mr-4 flex h-5 w-5 items-center justify-center rounded-full border border-primary ${checked && '!border-4'
              }`}
          >
            <span className="h-2.5 w-2.5 rounded-full bg-white dark:bg-transparent"></span>
          </div>
        </div>
        {title}
      </label>
    </div>
  );
};

export default CheckboxFive;
