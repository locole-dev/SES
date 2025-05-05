import { SVGProps } from "react";

export const Code = (props: SVGProps<SVGSVGElement>) => (
    <svg
        width="24" height="24" viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
        {...props}>
        <path d="M10.5 16.5L13.5 7.5" stroke="currentColor" strokeLinecap="round" />
        <path d="M16.5 8.5L19.5 12L16.5 15.5M7.5 8.5L4.5 12L7.5 15.5" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" />
    </svg>

);