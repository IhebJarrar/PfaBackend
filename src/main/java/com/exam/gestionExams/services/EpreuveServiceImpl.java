package com.exam.gestionExams.services;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import com.exam.gestionExams.model.Surveillant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.repository.EpreuveRepository;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Service
public class EpreuveServiceImpl implements EpreuveService {

	@Autowired
    EpreuveRepository epreuveRepository;

	
	public List<Epreuves> getAllEpreuves() {
		return epreuveRepository.findAll();
	}

	@Override
	public Epreuves getEpreuve(Long id) {
		return epreuveRepository.findOne(id);
	}

	@Override
	public void epreuveToPdf(Epreuves e) {
		String result = "/home/iheb/Bureau/pdf/epreuve"+e.getId()+".pdf";//C:/Users/mohamed/Desktop/epreuve
		try {

			// step 1
			Rectangle r=new Rectangle(880, 490);
			Document document = new Document(r);
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(result));
			// step 3
			document.open();

			// header
			String s="---------------------------------------------------";
			Paragraph p= new Paragraph(s+s+s+"Enveloppe"+s, new Font(FontFamily.HELVETICA, 11));
			p.setAlignment(Element.ALIGN_TOP);
			Paragraph p2= new Paragraph("Ecole Pluridisciplinaire Internationale\n", new Font(FontFamily.HELVETICA, 11));
			document.add(p);
			document.add(p2);

			// salle rectangle
			Font fontLabel = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
			Font fontData = new Font(FontFamily.TIMES_ROMAN, 18, Font.ITALIC, BaseColor.GRAY);
			PdfPTable table = new PdfPTable(1);
			Chunk chunkLabel = new Chunk("Salle :",fontLabel);
			Paragraph paragrapheSalle= new Paragraph();
			paragrapheSalle.add(chunkLabel);
			Chunk chunkData = new Chunk("",fontData);

			if(e.getLocal()!=null) {
				chunkData.append(e.getLocal().getNom()+"E"+e.getLocal().getEtage());
			}
			paragrapheSalle.add(chunkData);
			PdfPCell cell = new PdfPCell(paragrapheSalle);
			cell.setRowspan(400);
			table.addCell(cell);
			table.setWidthPercentage(20);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			document.add(table);
			document.add(new Paragraph("\n"));

			//matrice du centre
			PdfPTable donnéeTable = new PdfPTable(2);
			donnéeTable.setWidthPercentage(100);

			//donnée a gauche
			PdfPCell  cellGauche=new PdfPCell ();
			cellGauche.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cellGauche.setPadding(0);
			cellGauche.setBorderWidthLeft(10);
			cellGauche.setBorder(PdfPCell.NO_BORDER);
			//le tableau du gauche
			PdfPTable rectDonnée = new PdfPTable(1);
			Paragraph paragrapheDataTableLeft= new Paragraph();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateFormatted = formatter.format(e.getCreneau().getDate());
			Chunk chunkLabelDate = new Chunk("Date :",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelDate);
			Chunk chunkDate = new Chunk(dateFormatted,fontData);
			paragrapheDataTableLeft.add(chunkDate);
			Chunk chunkLabelSeance = new Chunk("\nSeance : ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelSeance);
			Chunk chunkSeance = new Chunk(e.getCreneau().getSeance().getHeureDebut(),fontData);
			paragrapheDataTableLeft.add(chunkSeance);
			Chunk chunkLabelDuree = new Chunk("Durée(h) :  ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelDuree);
			Chunk chunkDuree = new Chunk(e.getDuree().toString(),fontData);
			paragrapheDataTableLeft.add(chunkDuree);
			Chunk chunkLabelParcours = new Chunk("\nParcours :  ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelParcours);
			Chunk chunkParcours = new Chunk(e.getGroupe().getClasse().getNivSpecialite().getSpecialite().getNom(),fontData);
			paragrapheDataTableLeft.add(chunkParcours);
			Chunk chunkLabelNiveau = new Chunk("\nNiveau : ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelNiveau);
			Chunk chunkNiveau = new Chunk(String.valueOf(e.getGroupe().getClasse().getNivSpecialite().getNiveau().getNiveau()),fontData);
			paragrapheDataTableLeft.add(chunkNiveau);
			Chunk chunkLabelNumEpr = new Chunk("\nN° EPV : ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelNumEpr);
			Chunk chunkNumEpr = new Chunk(String.valueOf(e.getId()),fontData);
			paragrapheDataTableLeft.add(chunkNumEpr);
			Chunk chunkLabelNomEpr = new Chunk("\nNom Epreuve : ",fontLabel);
			paragrapheDataTableLeft.add(chunkLabelNomEpr);
			Chunk chunkNom = new Chunk(e.getNom()+"\n",fontData);
			paragrapheDataTableLeft.add(chunkNom);
			PdfPCell cellrectDonnée = new PdfPCell(paragrapheDataTableLeft);
			rectDonnée.addCell(cellrectDonnée);
			//le reste du gauche
			Paragraph groupe=new Paragraph();
			Chunk chunkLabelGroupe = new Chunk("\nGroupe : ",fontLabel);
			Chunk chunkGroupe = new Chunk(e.getGroupe().getNom()+"\n",fontData);
			groupe.add(chunkLabelGroupe);
			groupe.add(chunkGroupe);
			Paragraph details=new Paragraph("Détails de l'épreuve : \n\n\n\n\n\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.ITALIC));
			//finaliser la gauche
			cellGauche.addElement(rectDonnée);
			cellGauche.addElement(groupe);
			cellGauche.addElement(details);
			donnéeTable.addCell(cellGauche);

			//partie droite
			PdfPCell  cellDroite=new PdfPCell ();
			cellDroite.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cellDroite.setPaddingLeft(20);
			cellDroite.setBorder(PdfPCell.NO_BORDER);
			cellDroite.setRowspan(800);

			//donnée simple
			Paragraph dataDroite =new Paragraph("Nb Etudiant :"+e.getGroupe().getCapacite()+"           Nb Présents : ......\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			Paragraph dataDroite3 =new Paragraph("Absent(s) : \n\n",new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));
			//cas des surveillants
			List<Surveillant>surv=e.getSurveillants();
			String survResponsable="";
			StringBuilder surveillants=new StringBuilder("\n");
			if(surv!=null)
			{
				switch (surv.size()) {
					case 1:
					{
						surveillants.append("   "+surv.get(0).getNom()+"\n\n");
						break;
					}
					case 2:
					{
						surveillants.append("   "+surv.get(0).getNom()+"\n   "+surv.get(1).getNom()+"\n");
						break;
					}

					case 3:
					{
						surveillants.append("   "+surv.get(0).getNom()+"\n   "+surv.get(1).getNom()+"\n   "+surv.get(2).getNom()+"\n");
						break;
					}
					default:
						break;

				}
			}

			Paragraph dataDroite2 =new Paragraph("Enseignant(s) Responsable(s) : "+survResponsable+"\nSurveillant(s) : "+surveillants,new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD));

			//tableau absent
			PdfPTable tableAbsent = new PdfPTable(1);
			PdfPCell cellabsent = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n"));
			tableAbsent.addCell(cellabsent);

			//finaliser la droite
			cellDroite.addElement(dataDroite);
			cellDroite.addElement(dataDroite2);
			cellDroite.addElement(dataDroite3);
			cellDroite.addElement(tableAbsent);
			donnéeTable.addCell(cellDroite);
			//finaliser matrice centrale
			document.add(donnéeTable);


			// footer
			String s1="------------------------------------------------------";
			Paragraph p1= new Paragraph(s1+s1+s1+s1+" Transmis Le : .................................... "
					+ "					Signature : ............................................."	, new Font(FontFamily.HELVETICA, 11));
			p1.setAlignment(Element.ALIGN_BOTTOM);
			document.add(p1);

			//final step
			document.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void ListepreuvesToPdf(List<Epreuves> liste) {
		if(liste!=null)
		{
			for(Epreuves e:liste)
			{
				epreuveToPdf(e);
			}
		}
	}
}
