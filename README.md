# AuqueMia

## Sobre o AuqueMia

O projeto AuqueMia foi desenvolvido para organizar o fluxo de uma clínica veterinária. Ele permite o cadastro, consulta, atualização e exclusão (CRUD) de informações essenciais para o funcionamento diário de uma clínica.

## Funcionalidades

* *Gestão de Clientes:* Cadastro e controle de tutores.
* *Gestão de Pacientes:* Cadastro e histórico dos animais.
* *Profissionais:* Controle de veterinários associados.
* *Atendimentos:* Registro e gerenciamento de consultas.
* *Estoque/Farmácia:* Controle de medicamentos disponíveis.

## Como Foi Criado

* **Linguagem:** Java
* **Interface Gráfica:** Java Swing
* **Banco de Dados:** PostgreSQL
  
## Estrutura 
O projeto segue o padrão DAO (Data Access Object) para separar a lógica de negócio da comunicação com o banco de dados:
* `auquemia.entidades`: Classes que moldam os objetos do sistema (Animal, Veterinario).
* `auquemia.DAO`: Classes responsáveis pelas operações SQL no banco de dados.
* `auquemia.view`: Telas e interfaces gráficas construídas em Java Swing.

Autor:

Pedro Henrique - www.linkedin.com/in/pedro-henrique-5b543a373
