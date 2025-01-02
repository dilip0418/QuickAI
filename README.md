
# QuickAI

Welcome to QuickAI, an app designed to make it super easy to generate creative content with AI! Whether you’re writing text or creating visuals, QuickAI helps bring your ideas to life with just a few words.

This project uses Spring Boot and Spring WebFlux for fast and efficient performance. Plus, it’s powered by Hugging Face’s amazing AI models. Additionally, **Swagger** is integrated to document and test the **RESTful API**.

## 🌟 Features

- **Text Generation**: Generate creative text based on a given prompt using advanced AI models.
- **Image Generation**: Generate images from text prompts with the help of AI models.
- **Swagger UI**: Interactive API documentation to easily test and interact with the app's API endpoints.
- **Reactive and Scalable**: Built with Spring WebFlux, the app can handle multiple requests asynchronously, making it efficient for large-scale use.

## 🛠 Technologies Used

- **Spring Boot**: Framework for building the backend API.
- **Hugging Face Inference API**: For quick and easy to use Inference APIs.
- **Spring WebFlux**: For reactive, non-blocking HTTP requests.
- **WebClient**: To communicate with external APIs (e.g., Hugging Face).
- **Swagger**: For documenting and testing the REST API.

## Setting Up and Running the Application 🏃‍♀️‍➡️

### Prerequisites

- **Java 22 or newer** installed on your machine.
- **Maven** installed to build the application.
- **API keys** for Hugging Face.

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/quickai.git
cd quickai
```

### 2. Configure API Keys

In your `application.properties` or `application.yml`, configure the API URL and API key for the Hugging Face API or another model provider.

```properties
huggingface.text.api.url=https://api-inference.huggingface.co/models/your-model-name
huggingface.api.key=your-api-key-here
huggingface.image.api.url=https://api-inference.huggingface.co/models/your-image-model-name
```

### Running the application with IDE? Skip the below steps and just use the build/run features available by UI.

### 3. Build the Application

```bash
./mvnw clean install
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

Once the application starts, it will be accessible at `http://localhost:8080`.

### 5. Access Swagger UI

- After running the application, open your browser and go to: `http://localhost:8080/swagger-ui/index.html`.
- This will show you an interactive Swagger UI where you can explore and test the API endpoints.

## API Endpoints ⭐

### 1. Generate Text

**POST /api/text**

Generate AI-powered text based on the provided prompt.

**Request Body**:
```json
{
  "prompt": "Your text prompt here"
}
```

**Response**:
```json
{
  "generated_text": "AI-generated content here"
}
```

### 2. Generate Image

**POST /api/generate-image**

Generate an AI-generated image based on the provided prompt.

**Request Body**:
```json
{
  "prompt": "Your image prompt here"
}
```

**Response**:
- Returns a **byte array** representing the generated image. If you're testing using Swagger-UI you will get a download option to save and use the generated image.


## 🌈  Build a Frontend for QuickAI!
Take QuickAI to the next level by creating a frontend:

- Choose Your Framework: React, Angular, or even basic HTML/JavaScript.
- Design the UI: Add input fields for prompts and display results dynamically.
- Connect to the API: Use the endpoints to interact with QuickAI.
- Show Your Work: Share your app with others and see what QuickAI can do!

## 🌐 Connect with Me

### Let’s connect! Feel free to check out my work or reach out via:

[![Portfolio](https://img.shields.io/badge/Portfolio-%230A66C2?style=for-the-badge&logo=About.me&logoColor=white)]([https://your-portfolio-link.com](https://dilip-sudheer.netlify.app/))
[![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/dilip-kumar-bk/)
[![Dev.to](https://img.shields.io/badge/Dev.to-%230A66C2?style=for-the-badge&logo=dev.to&logoColor=white)](https://dev.to/dilipkumar_0418)