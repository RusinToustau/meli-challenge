## Mercadolibre Challenge


| GithubActions | Coverage | Quality Gate|
|---------------|----------|-------------|
|![build_and anlaize workflow](https://github.com/RusinToustau/meli-challenge/actions/workflows/build_and_analyze.yml/badge.svg)|[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=RusinToustau_meli-challenge&metric=coverage)](https://sonarcloud.io/dashboard?id=RusinToustau_meli-challenge)|[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=RusinToustau_meli-challenge&metric=alert_status)](https://sonarcloud.io/dashboard?id=RusinToustau_meli-challenge)|


https://user-images.githubusercontent.com/28780954/121982007-15703c00-cd65-11eb-969f-aabdacc6f1e9.mp4

La idea de armar esta app surge a partir de un challenge que recibí esta semana por parte de mercado libre. 

El fin de la misma es la de de pode mostrar el flujo de una búsqueda [cumpliendo con el enunciado](https://github.com/RusinToustau/meli-challenge/blob/feature/add_documentation/docs/enunciado.md).

Para comprende el flujo mirar la documentación [más info del flujo...](https://github.com/RusinToustau/meli-challenge/blob/feature/add_documentation/docs/FLOW.md)


## Arquitectura de app

<img src="https://user-images.githubusercontent.com/28780954/121982153-5f592200-cd65-11eb-8ca6-837e42a42f2d.png" width="600">

## Estructura del proyecto

<img width="292" alt="Captura de pantalla 2021-06-14 a la(s) 23 21 16" src="https://user-images.githubusercontent.com/28780954/121983203-50736f00-cd67-11eb-9828-b48854d1995d.png">


## Testing y Coverage 

Inspirados un poco [en este tutorial](https://about.codecov.io/blog/code-coverage-for-android-development-using-kotlin-jacoco-github-actions-and-codecov/) integramos jacoco test report a nuestra App. 

**Comando para generar reporte:**`./gradlew clean jacocoTestReport`

## SonarCloud y CI

También está integrada con SonarCloud. 

¿ Por qué SonarCloud ?

Al ser un repositorio público tenemos posibilidad de usar esta herramienta en forma gratuita.  

Para porder integrar el reporte de Jacoco con Sonar hubo que utilizar otra herramienta de integración contínua. en este caso usamos [Github Actions](https://github.com/features/actions).


| |  |
| ------ | ------ |
| <img width="292" alt="Captura de Pantalla 2021-06-16 a la(s) 17 23 48" src="https://user-images.githubusercontent.com/28780954/122293398-584d2380-cecd-11eb-8a9a-c15a53cac867.png"> | <img width="400" alt="Captura de Pantalla 2021-06-16 a la(s) 18 16 06" src="https://user-images.githubusercontent.com/28780954/122295380-80d61d00-cecf-11eb-842a-60f95ff877bd.png"> |

### Integración

Se empezó una integracion con [GitActions](https://docs.github.com/es/actions) y la herrmainta [SonarCloud](https://sonarcloud.io/dashboard?id=RusinToustau_meli-challenge) en la que se continuará trabajando generar mas jobs y lograr ingrar el reporte de coverage con Jacoco. 


## Librerías
- [Android Support Library](https://developer.android.com/topic/libraries/support-library/index.html)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
- [Retrofit for REST api communication](https://square.github.io/retrofit/)
- [Glide for image loading](https://github.com/bumptech/glide)
- [Paging2](https://developer.android.com/topic/libraries/architecture/paging)
- [espresso for UI tests](https://google.github.io/android-testing-support-library/docs/espresso/)
- [Mockito for mocking in tests](https://site.mockito.org/)
