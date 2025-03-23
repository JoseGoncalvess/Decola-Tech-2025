import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { dataFake} from '../../../data/data_fake';

@Component({
  selector: 'app-content',
  imports: [RouterModule],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent implements OnInit {
  contentPhoto : string ="";
  contentTitle : string ="";
  contentDescription : string ="";
  private id : string | null = "0"

  constructor(
    private router: ActivatedRoute
  ){}

  ngOnInit() {
    this.router.paramMap.subscribe(value => {
      this.id = value.get('id');
      this.getItemComponent(this.id || '1');
  })
  }

  private getItemComponent(id:string | null){
    let result = dataFake.find(item => item.id.toString() == this.id);

    this.contentPhoto = result?.img || "";
    this.contentTitle = result?.title || "";
    this.contentDescription = result?.description || "";
}


}


