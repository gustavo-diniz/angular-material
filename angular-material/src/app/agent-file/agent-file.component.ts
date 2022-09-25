import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { ViewChild } from '@angular/core';
import { Agente } from '../model/agente';


@Component({
  selector: 'app-agent-file',
  templateUrl: './agent-file.component.html',
  styleUrls: ['./agent-file.component.css']
})
export class AgentFileComponent implements OnInit {


  @ViewChild('fileUpload')
  myInputVariable!: ElementRef;

  fileName = '';
  uploadProgress: number;
  uploadFinish: boolean;
  sucessMsg: string;

  xml: any;


  constructor(private http: HttpClient, private api: ApiService) {
    this.uploadProgress = 0;
    this.uploadFinish = false;
    this.sucessMsg = '';
  }


  cancelUpload() {
    console.log(this.myInputVariable.nativeElement.files);
    this.myInputVariable.nativeElement.value = "";
    console.log(this.myInputVariable.nativeElement.files);

    this.uploadProgress = 0;
  }

  reset() {
    this.uploadProgress = 0;

  }


  xmlInputFiles(fileInputEvent: any) {
    this.uploadFinish = false;
    this.uploadProgress = 1;


    console.log(fileInputEvent.target.files.length);
    let totalArquivos = fileInputEvent.target.files.length;


    for (let file in fileInputEvent.target.files) {
      let selectedFile = fileInputEvent.target.files[file];
      const reader = new FileReader();

      if (reader != null) {

        reader.onload = (e) => {
          var text = this.validaXml(reader);
          
          this.api.enviarAgentes(text).subscribe((callback: Agente) => {
            if (callback.statusRetorno != null) {
              console.log("Arquvios Enviados !");

              this.uploadProgress = 100 / totalArquivos;
              totalArquivos = totalArquivos - 1;

              if (this.uploadProgress == 100) {
                this.uploadProgress = 0;
                this.uploadFinish = true;
                this.sucessMsg = "Arquivos processados com sucesso !";
              }

            } else {
              console.log("Não foi possível enviar os arquivos !");
              this.uploadProgress = 0;
              this.uploadFinish = true;
              this.sucessMsg = "**** Não foi possivel processar os arquvivos !";
            }

          }, err => {
            console.log("Não foi possível enviar os arquivos !");
            this.uploadProgress = 0;
            this.uploadFinish = true;
            this.sucessMsg = "**** Não foi possivel processar os arquvivos !";
          });


        }
        reader.readAsText(selectedFile);

      }
    }


  }


  private validaXml(reader: FileReader) {
    var text = reader.result!.toString().trim();
    text = text.replace(/(\r\n|\n|\r)/gm, "");
    text = text.trim();

    let ctn = true;
    while (ctn) {
      let inicio = text.indexOf("<precoMedio>");
      let fim = text.indexOf("</precoMedio>");

      if (inicio == -1 || fim == -1) {
        ctn = false;
      } else {
        text = text.replaceAll(text.substring(inicio, fim + 13), "");
      }
    }
    return text;
  }





  ngOnInit(): void {

  }




}
