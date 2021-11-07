import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AuthService } from '../../auth/shared/auth.service';
import { DeletePostConfirmComponent } from '../delete-post-confirm/delete-post-confirm.component';
import { PostModel } from '../model/post.model';

@Component({
  selector: 'app-delete-post',
  templateUrl: './delete-post.component.html',
  styleUrls: ['./delete-post.component.css']
})
export class DeletePostComponent implements OnInit {

  isPostUser!: boolean

  @Input() post!: PostModel

  constructor(private dialog: MatDialog, private authService: AuthService) { }

  ngOnInit(): void {
    if (this.post.userName == this.authService.getUserName()) {
      this.isPostUser = true
    } else {
      this.isPostUser = false
    }
  }

  openConfirmDelete(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.autoFocus = true
    DIALOG_CONFIG.data = { postId: this.post.postId }
    this.dialog.open(DeletePostConfirmComponent, DIALOG_CONFIG)
  }
}
