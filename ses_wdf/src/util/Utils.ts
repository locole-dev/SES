class Utils {
  static async generateSha1Base64(text: string): Promise<string> {
    const encoder = new TextEncoder();
    const data = encoder.encode(text);
    const hashBuffer = await crypto.subtle.digest("SHA-1", data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const base64Hash = btoa(String.fromCharCode(...hashArray));
    return base64Hash;
  }
}

export default Utils;
