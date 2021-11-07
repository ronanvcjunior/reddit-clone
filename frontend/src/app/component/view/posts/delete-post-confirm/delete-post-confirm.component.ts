import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-delete-post-confirm',
  templateUrl: './delete-post-confirm.component.html',
  styleUrls: ['./delete-post-confirm.component.css']
})
export class DeletePostConfirmComponent implements OnInit {

  constructor(public dialog: MatDialogRef<DeletePostConfirmComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private postService: PostService) { }

  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialog.close()
  }

  deletePost(): void {
    this.postService.deletePost(this.data.postId).subscribe(() => {
      window.location.reload();
    }, err => {
      this.postService.mensagem('Não foi possível deletar a postagem, tente mais tarde!')
    })
  }

}
