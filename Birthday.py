#py Birthday.py
#The following program takes an excel sheet of names and birthdays and sends a birthday message to a friends email adress
#If the current day and month in a row is equal to Birthday day and month in Excel but the current year and the Year in Excel is not equal send a message to that row's email adress and increase the row's Year by one
#If the current day and month is equal to the Birthday day and month but the current year is equal to the Year in Excel do nothing because a message was already sent today
#VERY IMPORTANT
#Make sure you install the following packages using py -m pip install
#pandas, datetime, smtplib, email, os
#The following sourcecode is from https://medium.com/pythoneers/python-script-that-sends-a-birthday-message-to-your-friends-c1aa1b52fe57
#I added many of the comments
import pandas as pd
import datetime
import smtplib
from email.message import EmailMessage
import os

def sendEmail(to, sub, msg):
    print(f"email to {to} \nsend with subject: {sub}\n message: {msg}")
    email = EmailMessage()
    email['from'] = 'Charles Lejtman'
    email['to'] = f"{to}"
    email['subject'] = f'{sub}'

    email.set_content(f'{msg}')

    with smtplib.SMTP(host='smtp.gmail.com', port=587) as smtp:
        smtp.ehlo()
        smtp.starttls()
        #  VERY IMPORTANT (if you do not do this you will get an error
        #  1 Create a dummy gmail account
        #  2 In your dummy account Go to the following link https://www.google.com/settings/security/lesssecureapps make sure the switch is turned ON (if it is off you will not send the emails out)
        #  3 Enter  your dummy email adress and its password in the following line
        smtp.login('yourDummyAdress','yourDummyPassword')
        #Now you will be able to send out emails to any email adress even if their own lesssecureapps switch is turned off
        smtp.send_message(email)
        print("Email send")
    pass
if __name__ == "__main__":
    df = pd.read_excel("PythonBirthday.xlsx")
    print(df)
    today = datetime.datetime.now().strftime("%d-%m")
    #print(type(today))
    update = []
    yearnow =  datetime.datetime.now().strftime("%Y")
    #print(yearnow)
    for index, item in df.iterrows():
        #print(index,item['birthday'])
        bday = item['Birthday'].strftime("%d-%m")
        #print(type(bday))
        if(bday == today) and yearnow not in str(item["Year"]): 
            sendEmail(item['Email'] ,"Happy Birthday "+item["Name"], item['message'])
            update.append(index)
    for i in update:
        yr = df.loc[i, 'Year']
        #print(yr)
        df.loc[i,'Year'] = f"{yr}, {yearnow}"
        #print((df.loc[i, 'Year'])
    #print(df)
    df.to_excel("PythonBirthday.xlsx", index=False)
