/*******************************************************************************
 * ͼ��ѧ��ѧ���򣺶������߲ü��㷨
 * ��Ȩ����������������ʹ�á����ƻ򴫲������룬���뱣��ԭ��������
 * ���ߣ��ɶ�����ѧ��Ϣ����ѧԺ-�˷�  2010.8
*******************************************************************************/
 
#include <gl/glut.h>
#include <stdio.h>
#include "tools.h"
#include "gpoint2.h"
#include "gpolygon2.h"

// �ü����ھ���
GRect2d gWinRect;

// ����ζ������� 
GPolygon2d gPoly;

// �ü�����ζ������� 
GPolygon2d gCPoly;

// ������ѡ�еĿ��Ƶ� 
int gSelectedIndex = -1;

// Shift���Ƿ���
bool gIsShiftDown = false; 

void drawPoints(const GPolygon2d &points)
{
    for(int i=0; i<points.count(); i++)
    {
        // ���ƾ��ο��Ƶ�
        glColor3f(0, 0, 1); 
        gltRect2d(points[i].x()-5, points[i].y()-5,
                  points[i].x()+5, points[i].y()+5);
    }
}

void drawTags()
{
    char text[32];
    for(int i=0; i<gPoly.count(); i++)
    {
        // ��ע���� 
        sprintf(text, "(%d, %d)", (int)gPoly[i].x(), (int)gPoly[i].y());
        glColor3f(1, 0, 0);
        gltRasterText(gPoly[i].x()+10, gPoly[i].y()-5, text);
    }
}

// �����¼����� 
void displayEvent()
{
    int i, n;
    glClearColor(1, 1, 1, 0);
    glClear(GL_COLOR_BUFFER_BIT);
    
    glColor3f(0, 0, 1);
    gltRect2d(gWinRect.x0(), gWinRect.y0(), gWinRect.x1(), gWinRect.y1());
    
    drawPoints(gPoly);
    
    glColor3f(0, 0, 0);
    n = gPoly.count();
    for(i=0; i<n; i++)
    {
        gltLine2d(gPoly[i].x(), gPoly[i].y(),
            gPoly[(i+1)%n].x(), gPoly[(i+1)%n].y());
    }
    
    glColor3f(1, 0, 0);
    glLineWidth(2);
    n = gCPoly.count();
    for(i=0; i<n; i++)
    {
        gltLine2d(gCPoly[i].x(), gCPoly[i].y(),
            gCPoly[(i+1)%n].x(), gCPoly[(i+1)%n].y());
    }
    glLineWidth(1);

    glFlush();
    glutSwapBuffers();  // �������� 
}

// ����ߴ�仯�¼� 
void resizeEvent(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    gluOrtho2D(0, w, 0, h);
    glMatrixMode(GL_MODELVIEW);
    glutPostRedisplay();
}       

// ����ƶ��¼����� 
void mouseMoveEvent(int x, int y)
{
    int h = glutGet(GLUT_WINDOW_HEIGHT);
    y = h - y;
    if(gSelectedIndex >= 0)
    {
        gPoly[gSelectedIndex].set(x, y);
        glutPostRedisplay();
    }
}        

// ����¼����� 
void mouseEvent(int button, int state, int x, int y)
{
    int h = glutGet(GLUT_WINDOW_HEIGHT);
    y = h - y;
    if(button == GLUT_LEFT_BUTTON)
    {
        if(state == GLUT_DOWN)
        {
            int index = gPoly.findByCoord(x, y);
            if(index < 0)   // ��ӿ��Ƶ�
            {
                index = gPoly.count(); 
                gPoly.addLast(GPoint2d(x, y));
                gSelectedIndex = index;
            }
            else            // �޸�(ɾ��)���п��Ƶ� 
            {
                if(glutGetModifiers() & GLUT_ACTIVE_SHIFT) // ɾ��
                {
                    gPoly.remove(index);
                }
                else
                {
                    gSelectedIndex = index;
                }
            }
        }
        else if(state == GLUT_UP)
        {    
            if(gSelectedIndex >= 0)
            {
                gPoly[gSelectedIndex].set(x, y);
                glutPostRedisplay();
                gSelectedIndex = -1;
            }
        }            
        glutPostRedisplay();
    }
    else if(button == GLUT_RIGHT_BUTTON)
    {
        gltPolyClip2d(gPoly, gCPoly, gWinRect);
        glutPostRedisplay();
    }
        
}             

// openGL��ʼ������ 
void glInit()
{
    gWinRect.set(50, 60, 350, 260);
} 

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 300);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Polygon Clip");
	glInit();
	
	glutReshapeFunc(resizeEvent);
	glutDisplayFunc(displayEvent);
	
	glutMouseFunc(mouseEvent);
	glutMotionFunc(mouseMoveEvent);
	
	glutMainLoop();
	
	return 0;
}
