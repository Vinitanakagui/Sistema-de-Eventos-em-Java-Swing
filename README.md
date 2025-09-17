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

O sistema utiliza MySQL. Certifique-se de que o MySQL está instalado e em execução.
Após a instalação, configure as credenciais no arquivo persistence.xml:

No arquivo persistence.xml, localize a seção <properties> e ajuste conforme necessário:
  
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <properties>
    <property name="javax.persistence.jdbc.url" 
              value="jdbc:mysql://localhost:3306/db_jpa?useSSL=false&amp;allowPublicKeyRetrieval=true&amp;createDatabaseIfNotExist=true&amp;serverTimezone=UTC&amp;serverTimezone=America/Sao_Paulo"/>
    <property name="javax.persistence.jdbc.user" value="SEU_USUARIO_AQUI"/> <!-- Altere este -->
    <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/> <!-- Altere este -->
    <property name="javax.persistence.jdbc.password" value="SUA_SENHA_AQUI"/>
  </properties>
  ```

### 2. Acesso ao Sistema

O sistema já possui um usuário administrador padrão:

**Usuário**: admin

**Senha**: 123

Com ele, é possível acessar o painel administrativo, cadastrar semanas e eventos.
Novos participantes podem se cadastrar diretamente na tela de login, clicando em "Primeiro Acesso".
