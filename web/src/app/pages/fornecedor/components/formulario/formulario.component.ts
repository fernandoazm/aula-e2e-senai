import { FornecedorService } from './../../../../service/fornecedor.service';
import { FornecedorModel } from './../../../../model/fornecedor-model';
import { EnumSituacaoCadastro } from './../../../../enums/enum-situacao-cadastro';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

import { LoadingService } from 'src/app/service/loading.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss'],
})
export class FormularioComponent implements OnInit {
  fornecedor?: FornecedorModel;
  isLoading: boolean = false;
  situacoesCadastro = [
    { name: 'Ativo', code: EnumSituacaoCadastro.ATIVO },
    { name: 'Inativo', code: EnumSituacaoCadastro.INATIVO },
  ];

  form: FormGroup = this.formBuilder.group({
    nomeFantasia: new FormControl(null, [
      Validators.required,
      Validators.minLength(2),
    ]),
    razaoSocial: new FormControl(null, [
      Validators.required,
      Validators.minLength(2),
    ]),
    cnpj: new FormControl(null, [
      Validators.required,
      Validators.minLength(14),
    ]),
    fone: new FormControl(null, [
      Validators.required,
      Validators.minLength(11),
    ]),
    email: new FormControl(null, [
      Validators.required,
      Validators.minLength(2),
    ]),
    situacaoCadastro: new FormControl(EnumSituacaoCadastro.ATIVO, [Validators.required]),
  });

  nomeFantasia = this.form.controls['nomeFantasia'];
  razaoSocial = this.form.controls['razaoSocial'];
  cnpj = this.form.controls['cnpj'];
  fone = this.form.controls['fone'];
  email = this.form.controls['email'];
  situacaoCadastro = this.form.controls['situacaoCadastro'];

  constructor(
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private formBuilder: FormBuilder,
    private loadingService: LoadingService,
    private fornecedorService: FornecedorService
  ) {}

  ngOnInit(): void {
    this.fornecedor = this.config.data.value;
    if (this.fornecedor) {
      this.nomeFantasia.setValue(this.fornecedor.nomeFantasia);
      this.razaoSocial.setValue(this.fornecedor.razaoSocial);
      this.cnpj.setValue(this.fornecedor.cnpj);
      this.fone.setValue(this.fornecedor.fone);
      this.email.setValue(this.fornecedor.email);
      this.situacaoCadastro.setValue(this.fornecedor.situacaoCadastro);
    }
  }

  onSubmit(): void {
    if (this.form.valid) {
      if (this.fornecedor) {
        const sub = this.fornecedorService
          .alterar(this.fornecedor.id, this.form.getRawValue())
          .subscribe(() => this.ref.close(true));
        this.load(sub);
      } else {
        const sub = this.fornecedorService
          .cadastrar(this.form.getRawValue())
          .subscribe(() => {
            this.config.data.reloadGrid();
            this.form.reset();
          });
        this.load(sub);
      }
    } else {
      this.form.markAllAsTouched();
    }
  }

  private load(sub: Subscription): void {
    this.loadingService.load(sub, (x) => (this.isLoading = x));
  }
}
