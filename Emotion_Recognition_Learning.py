import cv2
import dlib
from keras.models import model_from_json
import numpy as np

# Load the pre-trained facial landmarks predictor from dlib
predictor = dlib.shape_predictor("/content/shape_predictor_68_face_landmarks.dat")

# Load the pre-trained facial expression recognition model's architecture
with open("/content/model.json", "r") as json_file:
    model_json = json_file.read()
    expression_model = model_from_json(model_json)

# Load the pre-trained facial expression recognition model's weights
expression_model.load_weights("/content/model_weights.h5")
detector = dlib.get_frontal_face_detector()

# Function to detect facial landmarks
def detect_landmarks(frame):
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    faces = detector(gray)
    
    landmarks = []
    for face in faces:
        shape = predictor(gray, face)
        landmarks.append(shape)
    
    return faces, landmarks

# Function to recognize facial expression
def recognize_expression(face_roi):
    # Preprocess the face region of interest if needed
    # ...

    # Resize the face image to match the input size of the expression model
    face_roi = cv2.resize(face_roi, (48, 48))
    
    # Convert the face image to grayscale
    face_roi_gray = cv2.cvtColor(face_roi, cv2.COLOR_BGR2GRAY)
    
    # Normalize pixel values to be in the range [0, 1]
    face_roi_gray = face_roi_gray / 255.0
    
    # Expand dimensions to match the input shape of the model
    face_roi_expanded = np.expand_dims(face_roi_gray, axis=0)
    
    # Perform facial expression recognition using the pre-trained model
    predictions = expression_model.predict(face_roi_expanded)
    
    # Get the index of the predicted emotion
    predicted_label = np.argmax(predictions)
    
    return predicted_label

# Function to analyze facial expressions in a video
def analyze_facial_expressions(video_path):
    cap = cv2.VideoCapture(video_path)
    results = []  # List to store recognized expressions
    
    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break
        
        # Detect facial landmarks
        faces, landmarks = detect_landmarks(frame)
        
        for face, shape in zip(faces, landmarks):
            # Extract the face region of interest
            face_roi = frame[face.top():face.bottom(), face.left():face.right()]
            
            # Recognize facial expression
            predicted_label = recognize_expression(face_roi)
            results.append(predicted_label)
            
            # Draw the recognized expression label on the frame
            expression_label = f"Expression: {predicted_label}"
            cv2.putText(frame, expression_label, (face.left(), face.top() - 10),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
            
            # Draw facial landmarks on the frame
            for i in range(68):
                x, y = shape.part(i).x, shape.part(i).y
                cv2.circle(frame, (x, y), 1, (0, 0, 255), -1)
        
        # Display the result
        #cv2.imshow("Facial Expression Analysis", frame)
        
        # Break the loop if 'q' is pressed
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    
    cap.release()
    cv2.destroyAllWindows()
    
    return results

# Specify the path to the video
video_path = "/content/4.mp4"
expression_results = analyze_facial_expressions(video_path)

# Print the recognized expressions
print("Recognized Expressions:", expression_results)
