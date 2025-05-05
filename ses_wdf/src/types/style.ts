export type StyleData = {
    lablel?: TextStyle;
    header?: TextStyle;
    content?: TextStyle;
    hint?: TextStyle;
    button?: TextStyle;
    cancelButton?: TextStyle;
    textField?: TextStyle;
    error?: TextStyle;
    font?: string;
}

export type TextStyle = {
    font?: string;
    size?: string;
    color?: string;
    fontWeight?: string;
    fontStyle?: string;
    type?: string;
    background?: string;
    borderColor?: string;
}