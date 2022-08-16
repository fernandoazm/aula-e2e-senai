import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeModule } from './home/home.module';
import { BancoModule } from './banco/banco.module';
import { FornecedorModule } from './fornecedor/fornecedor.module';

@NgModule({
  declarations: [],
  imports: [CommonModule, HomeModule, BancoModule, FornecedorModule],
})
export class PagesModule {}
