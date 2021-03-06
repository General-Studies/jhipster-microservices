import { IPost } from 'app/shared/model/blog/post.model';

export interface ITag {
  id?: string;
  name?: string;
  entries?: IPost[];
}

export class Tag implements ITag {
  constructor(public id?: string, public name?: string, public entries?: IPost[]) {}
}
