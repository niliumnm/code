/*******************************************************************************
 * ͼ��ѧ��ѧ����ɨ����ת����� 
 * ��Ȩ����������������ʹ�á����ƻ򴫲������룬���뱣��ԭ��������
 * ���ߣ��ɶ�����ѧ��Ϣ����ѧԺ-�˷�  2010.8
*******************************************************************************/
#include <gl/glut.h>
#include <stdio.h>
#include <windows.h>
#include "tools.h"
#include "gpoint2.h"
#include "gpolygon2.h"

#include "gpolygon2.h" 

// �������Ƶ����� 
GPolygon2i  gPolygon;
// ������ѡ�еĿ��Ƶ�  
int gSelectedIndex = -1;
// Shift ���Ƿ��� 
bool gIsShiftDown = false;
// �Ƿ�����ɶ������� 
bool gIsFillPolygon = false;


void drawPoints()
{
    for(int i=0; i<gPolygon.count(); i++)
    {
        // ���ƾ��ο��Ƶ�
        glColor3f(0, 0, 1); 
        gltRect2d(gPolygon[i].x()-5, gPolygon[i].y()-5,
                  gPolygon[i].x()+5, gPolygon[i].y()+5);
    }
}

void drawTags()
{
    char text[32];
    for(int i=0; i<gPolygon.count(); i++)
    {
        // ��ע���� 
        sprintf(text, "(%d, %d)", (int)gPolygon[i].x(), (int)gPolygon[i].y());
        glColor3f(1, 0, 0);
        gltRasterText(gPolygon[i].x()+10, gPolygon[i].y()-5, text);
    }
}

void drawCtrlLines()
{
    int n = gPolygon.count();
    for(int i=0; i<n; i++)
    {
        glColor3f(0.6f, 0.6f, 0.6f);
        gltLine2d(gPolygon[i].x(), gPolygon[i].y(), 
                  gPolygon[(i+1)%n].x(), gPolygon[(i+1)%n].y());
    }
}

// �����¼����� 
void onDisplay()
{
    glClearColor(1, 1, 1, 0);
    glClear(GL_COLOR_BUFFER_BIT);

    // ������� 
    glColor3f(1, 1, 0);
    if (gIsFillPolygon) gltFillPolygon(gPolygon, gPolygon.count());
    // ���ƿ��Ƶ� 
    drawPoints();
    // ���ƶ���������
    drawCtrlLines();
    // ���ƶ����ע 
    drawTags();

    glutSwapBuffers();  // ��������  
}


// ����ߴ�仯�¼� 
void onReshape(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    gluOrtho2D(0, w, h, 0);
    glMatrixMode(GL_MODELVIEW);
    glutPostRedisplay();
}       

// ����ƶ��¼����� 
void onMotion(int x, int y)
{
    if (gSelectedIndex >= 0)
    {
        gPolygon[gSelectedIndex].set(x, y);
        glutPostRedisplay();
    }
}

// ����¼����� 
void onMouse(int button, int state, int x, int y)
{
    if (button == GLUT_LEFT_BUTTON)
    {
        if (state == GLUT_DOWN)
        {
            if (gIsFillPolygon)
            {
                gPolygon.clear();
                gIsFillPolygon = false;
            }

            int index = gPolygon.findByCoord(x, y);
            if (index < 0)   // ��ӿ��Ƶ� 
            {
                index = gPolygon.count();
                gPolygon.addLast(GPoint2i(x, y));
                gSelectedIndex = index;
                gIsFillPolygon = false;
            }
            else            // �޸�(ɾ��)���п��Ƶ�  
            {
                if (glutGetModifiers() & GLUT_ACTIVE_SHIFT) // ɾ�� 
                {
                    gPolygon.remove(index);
                }
                else
                {
                    gSelectedIndex = index;
                }
                gIsFillPolygon = false;
            }
        }
        else if (state == GLUT_UP)
        {
            if (gSelectedIndex >= 0)
            {
                gPolygon[gSelectedIndex].set(x, y);
                glutPostRedisplay();
                gSelectedIndex = -1;
            }
        }
        glutPostRedisplay();
    }
    else if (button == GLUT_RIGHT_BUTTON)
    {
        gIsFillPolygon = true;
        glutPostRedisplay();
    }
}

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 300);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Fill Polygon");
	
	glutReshapeFunc(onReshape);
	glutDisplayFunc(onDisplay);
	
	glutMouseFunc(onMouse);
	glutMotionFunc(onMotion);
	
	glutMainLoop();
	
	return 0;
}
