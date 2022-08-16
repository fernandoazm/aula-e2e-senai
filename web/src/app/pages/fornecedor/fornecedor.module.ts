import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FornecedorComponent } from './fornecedor.component';
import { FormularioModule } from './components/formulario/formulario.module';
import { TabelaModule } from './components/tabela/tabela.module';

@NgModule({
  declarations: [FornecedorComponent],
  imports: [CommonModule, FormularioModule, TabelaModule],
})
export class FornecedorModule {}
