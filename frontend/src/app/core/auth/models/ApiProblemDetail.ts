export interface ApiProblemDetail {
  type?: string;
  title: string;
  status: number;
  detail: string;
  timestamp?: string;
  errors?: Record<string, string>;
}
