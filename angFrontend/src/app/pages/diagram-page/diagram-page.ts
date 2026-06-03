import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-diagram-page',
  imports: [],
  templateUrl: './diagram-page.html',
  styleUrl: './diagram-page.css',
})
export class DiagramPage implements AfterViewInit {

  @ViewChild('canvas') canvas!: ElementRef<HTMLCanvasElement>;

  ngAfterViewInit() {

    const ctx = this.canvas.nativeElement.getContext('2d');

    if(!ctx) return;

    ctx.fillStyle = 'red';
    ctx.fillRect(10, 10, 150, 100);

  }

}
