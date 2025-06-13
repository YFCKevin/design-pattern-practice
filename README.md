# Design Pattern Practice

專案呈現了我在學習實作設計模式，包含以下四種常見的行為型與創建型模式：

- [State Pattern](#state-pattern)
- [Strategy Pattern](#strategy-pattern)
- [Chain of Responsibility Pattern](#chain-of-responsibility-pattern)
- [Command Pattern](#command-pattern)
- [Observer Pattern](#observer-pattern)

---


## State Pattern

**應用場景：訂單狀態管理功能**

- 利用狀態模式管理訂單從建立到完成的多種狀態轉換。

### 📊 狀態機圖：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/order-Statemachine.png" width="100%" alt="Order State Machine">
</p>

### 🧱 OOD 設計圖：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/order-state-OOD.png" width="100%" alt="Order State OOD">
</p>

---


## Strategy Pattern

**應用場景：送貨與資源上傳功能**

- 使用策略模式將可變的邏輯（例如不同的送貨方式或上傳方式）封裝為可替換的策略物件。

### 1️⃣ 送貨策略：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/delivery-strategy-OOD.png" width="100%" alt="Delivery Strategy OOD">
</p>

### 2️⃣ 資源上傳策略：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/content-upload-strategy.png" width="100%" alt="Content Upload Strategy">
</p>

---


## Chain of Responsibility Pattern

**應用場景：AI 分析問卷並回覆**

- 將每一個分析步驟（如關鍵字判斷、情緒分析等）封裝為獨立的類別，利用責任鏈特性，依序處理。

### 🧱 OOD 設計圖：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/quest-response-CoR.png" width="100%" alt="Questionnaire Response CoR">
</p>

---


## Command Pattern

**應用場景：影片觀看紀錄功能**

- 將「使用者觀影中的行為」封裝成指令，使其對應行為。

### 🧱 OOD 設計圖：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/video-command.png" width="100%" alt="Video Command OOD">
</p>


---

## Observer Pattern

**應用場景：討論區系統**

- 使用觀察者模式，當有新影片上傳時，自動通知訂閱該類別的使用者，例如發送 Email 、更新按讚數和修改最新留言時間等等。

### 🧱 OOD 設計圖：

<p align="center">
  <img src="https://github.com/YFCKevin/design-pattern-practice/blob/main/src/main/resources/static/img/discuss-observer.png" width="100%" alt="Discuss Observer OOD">
</p>

---

## 📌 備註

專案透過自身實作設計模式，加深對設計原則的理解與應用。


---

## 📝 TODO

預計未來實作以下設計模式：

- [x] 👁️ **Observer Pattern**（觀察者模式）  
- [ ] 🏭 **Factory Method Pattern**（工廠方法模式）
