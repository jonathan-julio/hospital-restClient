# Hospital Mais Próximo - Cliente

## Estrutura de Diretórios

```bash
.
├── dist       # programa compilado
├── docs       # arquivos para documentação
└── src        # código fonte
```

## Compilação

Com o Java 17 instalado, execute:

```bash
make build     # compila o programa usando o maven e move para a pasta dist/
```

## Execução

Após compilar o programa, a execução pode ser feita como no exemplo a seguir:

```bash
java -jar dist/hospital-client.jar --method POST --url http://localhost:8080/server/location --payload "{ \"latitude\": -5.8658099, \"longitude\": -35.2258257 }"

# OUTPUT SEMELHANTE
# {
#   "name": "Hospital Maranhão",
#   "vacancies": 1,
#   "location": {
#     "latitude": -5.6,
#     "longitude": -45.3
#   },
#   "avaliable": true
# }

```
