import { CommentModel } from "./comment.model";

export interface CommentPageModel {
    content: CommentModel[];
    totalElements: number,
    totalPages: number,
    size: number,
    number: number
}