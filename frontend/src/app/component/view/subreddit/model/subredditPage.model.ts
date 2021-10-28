import { SubredditModel } from "./subreddit.model";

export interface SubredditPageModel {
    content: SubredditModel[];
    totalElements: number,
    totalPages: number,
    size: number,
    number: number
}