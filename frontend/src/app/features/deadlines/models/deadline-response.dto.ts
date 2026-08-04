export default interface DeadlineResponseDto {
  id: number;
  applicationId: number;
  createdAt: Date;
  title: string;
  details: string;
  dueAt: Date;
  completed: boolean;
  position: string;
  company: string;
}
