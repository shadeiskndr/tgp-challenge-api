# Economic Data API

A robust API for accessing global economic indicators built with Java Spring Boot and MySQL.

![Demo](https://raw.githubusercontent.com/shadeiskndr/shadeiskndr.github.io/main/uploads/countryeconomicdashboard.gif)

To see the React.js, TypeScript, Vite frontend repository, visit [here](https://github.com/shadeiskndr/tgp-webclient)

## Overview

This API provides access to various global economic indicators, including:

- GDP growth rates
- Population growth rates
- Education expenditure percentages
- Inflation rates
- Labour force statistics

The system uses JWT token-based authentication to secure endpoints and provides comprehensive data filtering options.

## Architecture

The application consists of two main components:

1. **MySQL Database** - Stores economic indicators data from the World Bank
2. **Java Spring Boot Application** - Provides RESTful API endpoints to access the data
