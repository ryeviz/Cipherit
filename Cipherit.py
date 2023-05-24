import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QLabel, QCheckBox, QTextEdit, QSpinBox, QPushButton, QVBoxLayout, QWidget, QMessageBox, QHBoxLayout
from PyQt5.QtGui import QFont, QIcon
import random
import string

class Cipherit(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Cipherit")
        self.setGeometry(100, 100, 496, 586)
        self.setWindowIcon(QIcon("/icon.png"))
        
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)
        layout = QVBoxLayout(central_widget)
        
        self.title_label = QLabel("Password Generator", self)
        font = QFont()
        font.setBold(True)
        self.title_label.setFont(font)
        layout.addWidget(self.title_label)
        
        self.password_area = QTextEdit(self)
        self.password_area.setReadOnly(True)
        layout.addWidget(self.password_area)
        
        checkboxes_layout = QHBoxLayout()
        self.special_chars_checkbox = QCheckBox("Include Special Characters", self)
        checkboxes_layout.addWidget(self.special_chars_checkbox)
        
        self.alphanumeric_checkbox = QCheckBox("Include Alphanumeric Characters", self)
        checkboxes_layout.addWidget(self.alphanumeric_checkbox)
        
        self.lowercase_checkbox = QCheckBox("Include Lowercase Letters", self)
        checkboxes_layout.addWidget(self.lowercase_checkbox)
        
        self.uppercase_checkbox = QCheckBox("Include Uppercase Letters", self)
        checkboxes_layout.addWidget(self.uppercase_checkbox)
        
        layout.addLayout(checkboxes_layout)
        
        self.length_label = QLabel("Password Length:", self)
        layout.addWidget(self.length_label)
        
        self.length_layout = QHBoxLayout()
        
        self.length_spinbox = QSpinBox(self)
        self.length_spinbox.setFixedHeight(25)
        self.length_spinbox.setMinimum(1)  
        self.length_spinbox.setMaximum(9999)  
        self.length_spinbox.setValue(16)  
        self.length_layout.addWidget(self.length_spinbox)
        
        layout.addLayout(self.length_layout)
        
        self.num_passwords_label = QLabel("Number of Passwords:", self)
        layout.addWidget(self.num_passwords_label)
        
        self.num_passwords_layout = QHBoxLayout()
        
        self.num_passwords_spinbox = QSpinBox(self)
        self.num_passwords_spinbox.setFixedHeight(25)
        self.num_passwords_spinbox.setMinimum(1)
        self.num_passwords_spinbox.setMaximum(9999)
        self.num_passwords_spinbox.setValue(5)
        self.num_passwords_layout.addWidget(self.num_passwords_spinbox)
        
        layout.addLayout(self.num_passwords_layout)
        
        self.generate_button = QPushButton("Generate", self)
        self.generate_button.clicked.connect(self.generate_passwords)
        layout.addWidget(self.generate_button)
        
    def generate_passwords(self):
        self.password_area.clear()
        num_passwords = self.num_passwords_spinbox.value()
        password_length = self.length_spinbox.value()
        
        for _ in range(num_passwords):
            password = self.generate_random_password(password_length)
            self.password_area.append(password)
        
        if not self.special_chars_checkbox.isChecked() and not self.alphanumeric_checkbox.isChecked() and not self.lowercase_checkbox.isChecked() and not self.uppercase_checkbox.isChecked():
            QMessageBox.warning(self, "Error", "Please select at least one character type.")
    
    def generate_random_password(self, length):
        chars_to_use = ''
        
        if self.lowercase_checkbox.isChecked():
            chars_to_use += string.ascii_lowercase
            
        if self.uppercase_checkbox.isChecked():
            chars_to_use += string.ascii_uppercase
            
        if self.alphanumeric_checkbox.isChecked():
            chars_to_use += string.digits + string.ascii_letters
            
        if self.special_chars_checkbox.isChecked():
            chars_to_use += string.punctuation
            
        if not chars_to_use:
            return ""
        
        password = ''.join(random.choice(chars_to_use) for _ in range(length))
        return password

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = Cipherit()
    window.show()
    sys.exit(app.exec_())
