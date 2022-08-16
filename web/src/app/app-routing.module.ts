import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { BancoComponent } from './pages/banco/banco.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FornecedorComponent } from './pages/fornecedor/fornecedor.component';

const routes: Routes = [
  { path: '', component: HomeComponent, data: { breadcrumb: 'Home' } },
  { path: 'banco', component: BancoComponent, data: { breadcrumb: 'Banco' } },
  { path: 'fornecedor', component: FornecedorComponent, data: { breadcrumb: 'Fornecedor' } },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
