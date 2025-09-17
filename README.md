# Sistema de Gestão de Eventos em Java Swing

<p align="justify">
Este é um sistema desktop para gestão de eventos acadêmicos, desenvolvido em Java com interface gráfica em Swing. 
Ele oferece funcionalidades para a administração de semanas acadêmicas, eventos, organizadores e participantes, 
além de permitir a emissão de certificados.
</p>

---

## 👥 Perfis de Usuário

- **Administrador**
  - Cadastrar e gerenciar semanas de eventos
  - Criar eventos e atribuí-los a semanas
  - Gerenciar organizadores
  - Visualizar participantes inscritos

- **Participante**
  - Realizar cadastro no sistema
  - Visualizar eventos disponíveis
  - Inscrever-se em eventos
  - Emitir certificados de participação ou de eventos ministrados
  - Consultar carga horária complementar

---

## 🚀 Funcionalidades

- 🔐 Login de usuários (administradores e participantes)
- 📝 Cadastro de novos participantes
- 🖥️ Painel do Administrador:
  - Criar e gerenciar semanas de eventos
  - Adicionar eventos a semanas específicas
  - Visualizar participantes inscritos
- 👤 Painel do Participante:
  - Inscrição em eventos
  - Visualização dos eventos inscritos
  - Emissão de certificados
  - Consulta do total de horas complementares

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java  
- **Interface Gráfica:** Swing com FlatLaf  
- **Persistência de Dados:** JPA (EclipseLink)  
- **Banco de Dados:** MySQL  
- **Relatórios:** JasperReports  

---

## ⚙️ Configuração do Ambiente

### 1. Banco de Dados

O sistema utiliza **MySQL**. Certifique-se de ter o MySQL instalado e em execução.  
Após a instalação, configure as credenciais no arquivo:

