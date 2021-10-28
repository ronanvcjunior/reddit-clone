import { PostModel } from "./post.model";

export interface PostPageModel {
    content: PostModel[];
    totalElements: number,
    totalPages: number,
    size: number,
    number: number
}