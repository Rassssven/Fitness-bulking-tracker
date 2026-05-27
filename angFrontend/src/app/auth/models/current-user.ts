export interface CurrentUser {
  sub: string;
  id: number;
  firstName: string;
  role: string;
  exp: number;
  iat: number;
}