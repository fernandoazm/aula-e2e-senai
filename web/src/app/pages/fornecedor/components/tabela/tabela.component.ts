import { FornecedorService } from './../../../../service/fornecedor.service';
import { FornecedorModel } from './../../../../model/fornecedor-model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { ConfirmationService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { Table } from 'primeng/table';

import { LoadingService } from 'src/app/service/loading.service';
import { FormularioComponent } from '../formulario/formulario.component';

@Component({
  selector: 'app-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.scss'],
})
export class TabelaComponent implements OnInit {
  @ViewChild('tabela', { static: true }) tabela!: Table;

  columns = [
    { field: 'nomeFantasia', header: 'nomeFantasia', type: 'string' },
    { field: 'razaoSocial', header: 'razaoSocial', type: 'string' },
    { field: 'cnpj', header: 'cnpj', type: 'string' },
    { field: 'fone', header: 'fone', type: 'string' },
    { field: 'email', header: 'email', type: 'string' },
    { field: 'situacaoCadastro', header: 'situacaoCadastro', type: 'string' },
    { field: 'id', header: 'Ações', type: 'actions' },
  ];
  fornecedores: FornecedorModel[] = [];
  isLoading: boolean = false;

  constructor(
    private loadingService: LoadingService,
    private fornecedorService: FornecedorService,
    private confirmationService: ConfirmationService,
    private dialogService: DialogService
  ) {}

  ngOnInit(): void {
    this.reloadGrid();
  }
  openForm(value?: FornecedorModel): void {
    const ref = this.dialogService.open(FormularioComponent, {
      data: {
        value: value,
        reloadGrid: () => this.reloadGrid(),
      },
      header: value ? `Editar (${value.nomeFantasia})` : 'Novo Registro',
    });
    ref.onClose.subscribe((reload: boolean) => {
      if (reload) {
        this.reloadGrid();
      }
    });
  }

  pesquisar(texto: string): void {
    this.tabela.filterGlobal(texto, 'contains');
  }

  excluir(fornecedor: FornecedorModel): void {
    this.confirmationService.confirm({
      header: 'Deseja realmente excluir o registro?',
      message: 'Deseja realmente excluir o registro?',
      icon: 'pi pi-info-circle',
      rejectButtonStyleClass: 'p-button-text p-button-success',
      acceptButtonStyleClass: 'p-button-danger',
      accept: () => {
        const sub = this.fornecedorService
          .excluir(fornecedor.id)
          .subscribe(() => {
            this.reloadGrid();
          });
        this.load(sub);
      },
    });
  }

  reloadGrid(): void {
    const sub = this.fornecedorService
      .consultar()
      .subscribe((model: FornecedorModel[]) => {
        this.fornecedores = model;
      });
    this.load(sub);
  }

  getRowValue(value: any, field: string): string {
    if (field.includes('.')) {
      const field0 = field.substring(0, field.indexOf('.'));
      return this.getRowValue(
        value[field0],
        field.substring(field.indexOf('.') + 1)
      );
    }
    return value[field];
  }

  private load(sub: Subscription): void {
    this.loadingService.load(sub, (x) => (this.isLoading = x));
  }
}
