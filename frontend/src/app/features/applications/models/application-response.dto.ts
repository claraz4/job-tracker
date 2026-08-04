import { JobType } from '../types/JobType';
import { PriorityType } from '../types/PriorityType';
import { StatusType } from '../types/StatusType';
import { WorkModeType } from '../types/WorkModeType';

export default interface ApplicationResponseDto {
  id: number;
  position: string;
  company: string;
  location: string;
  jobType: JobType;
  priority: PriorityType;
  currentStatus: StatusType;
  dateApplied: Date;
  lastActivityAt: Date;
  notes: string;
  requirements: string;
  workMode: WorkModeType;
}
