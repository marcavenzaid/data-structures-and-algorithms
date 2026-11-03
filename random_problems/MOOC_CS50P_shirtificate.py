from fpdf import FPDF

name = input("Name: ")

pdf = FPDF(orientation="P", format="A4")
pdf.add_page()
pdf.set_font("helvetica", "B", 24)
pdf.cell(0, 20, "CS50 Shirtificate", align="C")
pdf.image("shirtificate.png", x=35, y=60, w=140)
pdf.set_font("helvetica", "B", 22)
pdf.set_text_color(255, 255, 255)
pdf.set_xy(0, 140)
pdf.cell(210, 10, name, align="C")
pdf.output("shirtificate.pdf")
