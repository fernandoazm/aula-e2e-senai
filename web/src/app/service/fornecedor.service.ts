import { tap } from 'rxjs';
import { take } from 'rxjs';
import { Observable } from 'rxjs';
import { FornecedorModel } from './../model/fornecedor-model';
import { MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FornecedorService {
  private baseUrl = '/fornecedor';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  consultar(): Observable<FornecedorModel[]> {
    return this.http.get<FornecedorModel[]>(`${this.baseUrl}`).pipe(take(1));
  }

  cadastrar(model: FornecedorModel): Observable<FornecedorModel> {
    return this.http.post<FornecedorModel>(`${this.baseUrl}`, model).pipe(
      tap(() => this.success('Fornecedor Cadastrado com Sucesso!')),
      take(1)
    );
  }

  alterar(id: string, model: FornecedorModel): Observable<FornecedorModel> {
    return this.http.put<FornecedorModel>(`${this.baseUrl}/${id}`, model).pipe(
      tap(() => this.success('Fornecedor Alterado com Sucesso!')),
      take(1)
    );
  }

  excluir(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`).pipe(
      tap(() => this.success('Fornecedor Excluido com Sucesso!')),
      take(1)
    );
  }

  private success(msg: string): void {
    this.messageService.add({
      severity: 'success',
      summary: msg,
    });
  }
}
