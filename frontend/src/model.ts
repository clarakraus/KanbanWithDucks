export enum EnumStatus {
    OPEN="OPEN",
    IN_PROGRESS= "IN_PROGRESS",
    DONE = "DONE"
}

export interface Task{
    task: string;
    description: string;
    status: EnumStatus;
    id?: string;
}